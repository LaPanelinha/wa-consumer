package dev.gabrielsancho.wpconsumer.controller.handler

import dev.gabrielsancho.wpconsumer.command.CommandFactory
import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.exception.CommandNotFoundException
import dev.gabrielsancho.wpconsumer.exception.HelpRequestedException
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.stereotype.Component

@Component
class MessageHandler(
        val commandFactory: CommandFactory,
        val service: WhatsappService
) {
    fun handleMessage(message: Message) {
        if (commandFactory.isCommand(message.text ?: ""))
            handleMessageCommand(message)
    }

    private fun handleMessageCommand(message: Message) {
        try {
            val command = commandFactory.getCommand(message.text ?: return)

            command.execute(message)
        } catch (ex: HelpRequestedException) {
            service.reply(message.from, message.id, "```${ex.help}```")
        } catch (ex: CommandNotFoundException) {
            // pass
        }
    }
}
