package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PingCommand(
        @Value("\${wa.command.prefix}") val commandPrefix: String,
        val service: WhatsappService
) : Command<PingCommand.PingArguments>() {
    override val alias = CommandAlias.PING_COMMAND

    override fun execute(message: Message) {
        PingArguments().loadArguments(message.text)
        sendPong(message)
    }

    private fun sendPong(message: Message) {
        service.reply(message.from, message.id, "Pong!")
    }

    inner class PingArguments : CommandArguments(commandPrefix, alias)
}
