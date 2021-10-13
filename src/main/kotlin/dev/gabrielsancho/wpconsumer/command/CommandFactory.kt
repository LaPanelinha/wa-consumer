package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CommandFactory {

    @Value("\${wa.command.prefix}")
    private lateinit var commandPrefix: String

    fun create(textContent: String, message: Message, service: WhatsappService): Command {
        val (commandType, args) = parseCommand(textContent)

        return when (commandType) {
            CommandType.SAY -> SayCommand(args, message, service)
            CommandType.PING -> PingCommand(message, service)
            CommandType.HELP -> HelpCommand(commandPrefix, message, service)
            CommandType.STICKER -> StickerCommand(message, args, service)
            CommandType.MESSAGE_TO -> MessageToCommand(args, service)
        }
    }

    private fun parseCommand(textContent: String): Pair<CommandType, List<String>> {
        val args = textContent.split(" ").toMutableList()

        val type = CommandType.fromAlias(args.removeAt(0).substringAfter(commandPrefix))

        return Pair(type, args)
    }
}
