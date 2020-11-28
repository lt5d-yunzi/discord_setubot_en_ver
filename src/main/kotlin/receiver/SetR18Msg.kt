package receiver

import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener
import utils.ConfigManager
import java.lang.Exception

class SetR18Msg : MessageCreateListener {
    override fun onMessageCreate(event: MessageCreateEvent) {
        if (event.messageAuthor.isServerAdmin) {
            if (event.messageContent.startsWith("!setr18 ", ignoreCase = true) ||
                event.messageContent.startsWith("！setr18 ", ignoreCase = true)
            ) {
                try {
                    when (event.messageContent.substring(8)) {
                        "0" -> {
                            ConfigManager.setConfig("r18", 0)
                            MessageBuilder().append("Set successfully! Current mode: normal").send(event.channel)
                        }
                        "1" -> {
                            ConfigManager.setConfig("r18", 1)
                            MessageBuilder().append("Set successfully! Current mode: R-18 only").send(event.channel)
                        }
                        "2" -> {
                            ConfigManager.setConfig("r18", 2)
                            MessageBuilder().append("Set successfully! Current mode: Mix").send(event.channel)
                        }
                        else -> MessageBuilder().append("The input parameter is illegal!").send(event.channel)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}