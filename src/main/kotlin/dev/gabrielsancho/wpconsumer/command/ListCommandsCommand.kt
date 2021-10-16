package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.extension.WaFormat
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.extension.toString
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ListCommandsCommand(
        @Value("\${wa.command.prefix}") val commandPrefix: String,
        val service: WhatsappService
) : Command<ListCommandsCommand.ListCommandsArguments>() {
    override val alias = CommandAlias.COMMANDS_COMMAND

    override fun execute(message: Message) {
        ListCommandsArguments().loadArguments(message.text)

        val commandsMessage = StringBuilder().apply {
            append("Lista de comandos disponíveis:".toString(WaFormat.BOLD), "\n\n")
            CommandAlias.values().forEach { alias ->
                val name = (alias.prettyName().toString(WaFormat.BOLD) + ": ")
                val args = alias.values.joinToString(", ") { "$commandPrefix$it" }.toString(WaFormat.MONOSPACE)

                append("$name[$args]", "\n")
            }
            append("\n", "Para mais ajuda digite o comando seguido de".toString(WaFormat.ITALIC), " ", "--help.".toString(WaFormat.MONOSPACE))
        }.toString()

        service.sendText(message.from, commandsMessage)
    }

    inner class ListCommandsArguments : CommandArguments(commandPrefix, alias)
}
