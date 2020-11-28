package utils

import com.alibaba.fastjson.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.lang.Exception
import kotlin.concurrent.fixedRateTimer

object ConfigManager {
    private var cfg = File("config.json")

    fun initialization() {
        if (!cfg.exists()) {
            cfg.createNewFile()
        }
        if (getConfig("token").toString().isEmpty() || getConfig("token") == null) {
            resetCfg()
            println("No login Token was detected.  Please enter your Bot Token:")
            val token = readLine()
            setConfig("token", token)
            println("Token Set successfully!")
        }
        if (getConfig("apikey").toString().isEmpty() || getConfig("apikey") == null) {
            println("Apikey is not set. Do you want to set it now? Y/N")
            println("注:It can also be used without setting apikey But the number of calls is very small Recommended https://api.lolicon.app Apply for apikey")
            while (true) {
                val s = readLine().toString().toLowerCase()
                if (s == "yes" || s == "y") {
                    println("-> Setting up apikey ")
                    println(" Please enter your apikey :")
                    val apikey = readLine().toString()
                    setConfig("apikey", apikey)
                    println("Apikey set successfully!")
                    break
                } else if (s == "n" || s == "no") {
                    setConfig("apikey", "")
                    println("-> Not set up Apikey")
                    break
                } else {
                    println(" Input error, please enter yes or no ")
                }
            }
        }
        if (getConfig("r18").toString().isEmpty() || getConfig("r18") == null) {
            setConfig("r18", 0)
        }
    }

    fun getConfig(): JSONObject? {
        return JSONObject.parseObject(cfg.readText(Charsets.UTF_8))
    }

    fun getConfig(key: String): Any? {
        return getConfig()?.get(key)
    }

    fun <T> setConfig(key: String, value: T) {
        try {
            val config = getConfig()
            config?.set(key, value)
            if (config != null) {
                writeCfg(config)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun writeCfg(jsonObject: JSONObject) {
        val osw = OutputStreamWriter(FileOutputStream(cfg), Charsets.UTF_8)
        osw.write(jsonObject.toJSONString())
        osw.flush()
        osw.close()
    }

    fun resetCfg() {
        writeCfg(JSONObject())
    }
}