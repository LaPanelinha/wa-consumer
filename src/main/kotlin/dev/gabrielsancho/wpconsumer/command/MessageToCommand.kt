package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.service.WhatsappService

class MessageToCommand(
        private val args: List<String>,
        private val service: WhatsappService
) : Command() {
    override fun execute() {
        val number = getNumber() ?: return
        val message = getMessage() ?: return
        service.sendText(number, message)
    }

    override fun canExecute() = getNumber() != null && getMessage() != null

    override fun getCantExecuteMessage() = "Formato inválido"

    private fun getNumber() = args.getOrNull(0)?.run { "$this@c.us" }
    private fun getMessage() = try {
        args.subList(1, args.size).joinToString(" ")
    } catch (ex: Exception) {
        null
    }
}
