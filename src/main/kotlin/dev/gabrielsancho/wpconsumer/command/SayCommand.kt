package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.service.WhatsappService

class SayCommand(
        private val args: List<String>,
        private val message: Message,
        private val service: WhatsappService
) : Command() {

    override fun execute() {
        service.sendText(message.from, args.joinToString(" "))
    }

    override fun canExecute() = args.isNotEmpty()

    override fun getCantExecuteMessage() = "Tu tem que falar o que eu tenho que falar"
}
