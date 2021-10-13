package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.service.WhatsappService

class HelpCommand(
        private val prefix: String,
        private val message: Message,
        private val service: WhatsappService
) : Command() {
    override fun execute() {
        val help = StringBuilder().apply {
            append("```")
            append("Lista de comandos:\n")
            CommandType.values().forEach { type ->
                append("${type.name}:\t[${type.aliases.joinToString(", ") { "$prefix$it" }}]")
                append("\n")
            }
            append("```")
        }.toString()

        service.reply(message.from, message.id, help)
    }

    override fun canExecute() = true

    override fun getCantExecuteMessage() = ""
}