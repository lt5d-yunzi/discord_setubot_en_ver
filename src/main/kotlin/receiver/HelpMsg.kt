package receiver

import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener

private const val HELP = """
    [command]
    < Random > Random ACGN pictures
    <!setr18 0/1/2> Allow r18(0:normal 1:R-18 only 2:Mix)
    <!help> Help menu
"""

class HelpMsg : MessageCreateListener {
    override fun onMessageCreate(event: MessageCreateEvent) {
        if (event.messageContent.equals("!help", ignoreCase = true) ||
            event.messageContent.equals("！help", ignoreCase = true)
        ) {
            event.channel.sendMessage(HELP)
        }
    }
}