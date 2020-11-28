package utils

import java.lang.Exception
import java.text.SimpleDateFormat

fun logi(msg: String) {
    val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())
    println("$time INFO:$msg")
}

fun loge(e: Exception) {
    val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())
    println("$time ERROR:${e.printStackTrace()}")
}