package dev.gabrielsancho.wpconsumer.service

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.StickerMetadata
import dev.gabrielsancho.wpconsumer.integration.WAIntegration
import dev.gabrielsancho.wpconsumer.integration.WaDecryptIntegration
import org.springframework.stereotype.Service

@Service
class WhatsappService(
        private val waIntegration: WAIntegration,
        private val waDecryptIntegration: WaDecryptIntegration
) {

    fun sendText(to: String, message: String) = waIntegration.sendText(to, message)
    fun reply(to: String, messageId: String, message: String) = waIntegration.reply(to, messageId, message, true)
    fun sendImageAsSticker(to: String, image: String, metadata: StickerMetadata?) =
            waIntegration.sendImageAsSticker(to, image, metadata)

    fun sendStickerFromUrl(to: String, url: String, metadata: StickerMetadata?) =
            waIntegration.sendStickerFromUrl(to, url, metadata)

    fun getMessageById(messageId: String) = waIntegration.getMessageById(messageId)

    fun decryptMedia(message: Message) = waDecryptIntegration.decryptMedia(message)
}
