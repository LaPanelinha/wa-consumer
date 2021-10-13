package dev.gabrielsancho.wpconsumer.service

import dev.gabrielsancho.wpconsumer.command.CommandFactory
import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.MessageType
import dev.gabrielsancho.wpconsumer.domain.StickerMetadata
import dev.gabrielsancho.wpconsumer.exception.CommandNotFoundException
import dev.gabrielsancho.wpconsumer.facade.WhatsappIntegrationFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class WhatsappService(
        private val wppFacade: WhatsappIntegrationFacade,
        private val commandFactory: CommandFactory
) {

    @Value("\${wa.command.prefix}")
    lateinit var commandPrefix: String

    fun sendText(to: String, message: String) = wppFacade.sendText(to, message)
    fun reply(to: String, messageId: String, message: String) = wppFacade.reply(to, messageId, message, true)
    fun sendImageAsSticker(to: String, image: String, metadata: StickerMetadata?) =
            wppFacade.sendImageAsSticker(to, image, metadata)

    fun sendStickerFromUrl(to: String, url: String, metadata: StickerMetadata?) =
            wppFacade.sendStickerFromUrl(to, url, metadata)

    fun getMessageById(messageId: String) = wppFacade.getMessageById(messageId)

    fun handleMessage(message: Message) = when (message.type) {
        MessageType.TEXT -> {
            if (message.content.startsWith(commandPrefix))
                handleCommand(message)
            else null
        }
        MessageType.VIDEO, MessageType.IMAGE -> {
            if (message.caption?.startsWith(commandPrefix) == true)
                handleCommand(message)
            else null
        }

        else -> null
    }

    private fun handleCommand(message: Message) {
        val textContent = when (message.type) {
            MessageType.TEXT -> message.content
            MessageType.VIDEO, MessageType.IMAGE -> message.caption
            else -> null
        } ?: return

        try {
            val command = commandFactory.create(textContent, message, this)

            if (!command.canExecute()) {
                sendText(message.from, command.getCantExecuteMessage())
                return
            }

            command.execute()
        } catch (ex: CommandNotFoundException) {
            sendText(message.from, "Rapaz, não sei o que é isso que tu tentou pedir aí não...")
        }
    }
}
