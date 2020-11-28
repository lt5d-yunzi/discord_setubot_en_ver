package utils

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit


val client = OkHttpClient.Builder()
    .callTimeout(180, TimeUnit.SECONDS)
    .connectTimeout(180, TimeUnit.SECONDS)
    .writeTimeout(180, TimeUnit.SECONDS)
    .readTimeout(180, TimeUnit.SECONDS)
    .build()

class Setu {
    private var url = ""
    private var p = 0
    private var uid = 0
    private var pid = 0
    private var title = ""
    private var author: String = ""
    private var location: String = ""
    lateinit var jsonObj: JSONObject
    var isSuccess: Boolean = false
    var msg: String = ""

    init {
        getSetu()
    }

    private fun getSetuInfo() {
        try {
            val request = Request.Builder()
                .url("https://api.lolicon.app/setu/?apikey=${getApiKey()}&r18=${getR18()}")
                .get()
                .build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                jsonObj = JSONObject.parseObject(response.body?.string())
                if (jsonObj.getInteger("code") == 0) {
                    isSuccess = true
                    val datas: JSONArray = jsonObj.getJSONArray("data")
                    val data = datas[0] as JSONObject
                    p = data.getInteger("p")
                    uid = data.getInteger("uid")
                    pid = data.getInteger("pid")
                    url = data.getString("url")
                    title = data.getString("title")
                    author = data.getString("author")
                } else {
                    msg = jsonObj.getString("msg")
                }
            }
        } catch (e: Exception) {
            loge(e)

        }
    }

    fun getShortName(url: String): String {
        val strings = url.split("/")
        return strings[strings.size - 1]
    }

    private fun getSetu() {
        getSetuInfo()
        val picDir = File("setu")
        val url = url
        if (!picDir.exists()) {
            picDir.mkdir()
        }
        val pic = File(picDir, getShortName(url))
        if (!pic.exists()) pic.createNewFile()
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val data: ByteArray = Objects.requireNonNull(response.body)!!.bytes()
            val fout = FileOutputStream(pic)
            fout.write(data)
            fout.flush()
            fout.close()
            location = pic.absolutePath
        } else {
            location = ""
            throw IOException(response.code.toString() + ":" + response.message)
        }
    }

    fun getPic(): File {
        return File(location)
    }

    override fun toString(): String {
        return """
            Title:$title
            Pid:$pid
            Author:$author
            Uid:$uid
            Page:$p
        """.trimIndent()
    }
}