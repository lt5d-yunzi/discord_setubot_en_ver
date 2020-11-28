package utils

fun initialization() {
    ConfigManager.initialization()
}

fun getToken(): String {
    return ConfigManager.getConfig("token").let { it?.toString() ?: "" }
}

fun getApiKey(): String {
    return ConfigManager.getConfig("apikey").let { it?.toString() ?: "" }
}

fun getR18(): String {
    return ConfigManager.getConfig("r18").let { it?.toString() ?: "0" }
}