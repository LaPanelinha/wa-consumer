package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.service.WhatsappService

class PingCommand(
        private val message: Message,
        private val service: WhatsappService
) : Command() {

    override fun execute() {
        service.reply(message.from, message.id, "PONG!")
    }

    override fun canExecute() = true

    override fun getCantExecuteMessage() = ""
}