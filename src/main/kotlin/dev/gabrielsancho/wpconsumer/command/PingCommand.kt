package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.stereotype.Component

@Component
class PingCommand(val service: WhatsappService) : Command() {
    override val alias = CommandAlias.PING_COMMAND

    override fun execute(message: Message) {
        service.react(message.id, "\uD83D\uDD95")
    }
}
