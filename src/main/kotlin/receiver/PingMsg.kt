package receiver

import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener

class PingMsg : MessageCreateListener {
    override fun onMessageCreate(event: MessageCreateEvent) {
        if (event.messageContent.equals("/ping", ignoreCase = true)) {
            event.channel.sendMessage("Pong!")
        }
    }
}