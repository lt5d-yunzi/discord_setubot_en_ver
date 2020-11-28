import org.javacord.api.DiscordApiBuilder
import receiver.CreateMsgReceiver
import utils.getApiKey
import utils.getToken
import utils.initialization
import utils.logi

fun main() {
    initialization()
    val api = DiscordApiBuilder()
        .setToken(getToken())
        .login().join()
    logi(" Landing successfully!")
    CreateMsgReceiver(api)
    logi("Initialization listener succeeded!")
    if (getApiKey().isEmpty()) {
        logi("You haven't set the apikey yet. Please go to api.lolicon.app obtain. ")
    }
}
