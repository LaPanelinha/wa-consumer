package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.MessageType
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import java.net.MalformedURLException
import java.net.URL

class StickerCommand(
        private val message: Message,
        private val args: List<String>,
        private val service: WhatsappService
) : Command() {
    override fun execute() {
        when (message.type) {
            MessageType.IMAGE -> sendImage(message)
            MessageType.TEXT -> {
                val firstArg = args.getOrElse(0) { "" }
                if (isValidUrl(firstArg))
                    sendUrl(message, firstArg)
                if (hasQuotedImage(message))
                    sendQuotedMessage(message, message.quotedMsgObj!!)
            }
            else -> {
            }
        }
    }

    private fun isValidUrl(str: String?) = try {
        URL(str)
        true
    } catch (ex: MalformedURLException) {
        false
    }

    private fun hasQuotedImage(message: Message) =
            message.quotedMsgObj != null && message.quotedMsgObj?.type == MessageType.IMAGE

    private fun sendImage(message: Message) {
        val body = message.contentDecrypted ?: message.body
        service.sendImageAsSticker(message.from, body, null)
    }

    private fun sendQuotedMessage(message: Message, quoted: Message) {
        val body = quoted.contentDecrypted ?: quoted.body
        service.sendImageAsSticker(message.from, body, null)
    }

    private fun sendUrl(message: Message, url: String) {
        service.sendStickerFromUrl(message.from, url, null)
    }

    override fun canExecute(): Boolean {
        if (message.type == MessageType.TEXT)
            return isValidUrl(args.getOrElse(0) { "" }) || hasQuotedImage(message)
        return message.type == MessageType.IMAGE
    }

    override fun getCantExecuteMessage() = "Imagem inválida."
}