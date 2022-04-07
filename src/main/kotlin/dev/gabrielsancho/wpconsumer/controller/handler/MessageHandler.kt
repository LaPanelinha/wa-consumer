package dev.gabrielsancho.wpconsumer.controller.handler

import dev.gabrielsancho.wpconsumer.command.CommandFactory
import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.MessageType
import dev.gabrielsancho.wpconsumer.exception.CommandNotFoundException
import dev.gabrielsancho.wpconsumer.exception.HelpRequestedException
import dev.gabrielsancho.wpconsumer.extension.WaFormat
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.extension.toString
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.stereotype.Component

@Component
class MessageHandler(
        val commandFactory: CommandFactory,
        val service: WhatsappService
) {
    fun handleMessage(message: Message) {
        if (commandFactory.isCommand(message.text ?: "")) {
            service.react(message.id, "\uD83D\uDD95\uD83C\uDFFB")
            handleMessageCommand(message)
        }
    }

    private fun handleMessageCommand(message: Message) {
        try {
            val command = commandFactory.getCommand(message.text ?: return)

            command.execute(message)
        } catch (ex: HelpRequestedException) {
            if (message.type == MessageType.BUTTONS_RESPONSE)
                service.sendText(message.from, ex.help.toString(WaFormat.MONOSPACE))
            service.reply(message.from, message.id, ex.help.toString(WaFormat.MONOSPACE))
        } catch (ex: CommandNotFoundException) {
            // pass
        }
    }
}
