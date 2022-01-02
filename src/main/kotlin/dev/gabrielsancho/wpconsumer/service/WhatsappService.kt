package dev.gabrielsancho.wpconsumer.service

import dev.gabrielsancho.wpconsumer.domain.StickerMetadata
import dev.gabrielsancho.wpconsumer.integration.WAIntegration
import org.springframework.stereotype.Service

@Service
class WhatsappService(
    private val waIntegration: WAIntegration
) {

    fun sendText(to: String, message: String) = waIntegration.sendText(to, message)
    fun reply(to: String, messageId: String, message: String) = waIntegration.reply(to, messageId, message, true)
    fun sendImageAsSticker(to: String, image: String, metadata: StickerMetadata?) =
        waIntegration.sendImageAsSticker(to, image, metadata)

    fun sendStickerFromUrl(to: String, url: String, metadata: StickerMetadata?) =
        waIntegration.sendStickerFromUrl(to, url, metadata)

    fun sendMp4AsSticker(to: String, mp4: String, metadata: StickerMetadata) =
        waIntegration.sendMp4AsSticker(to, mp4, metadata)

    fun getMessageById(messageId: String) = waIntegration.getMessageById(messageId)

    fun sendReplyWithMentions(to: String, message: String, replyToId: String) =
        waIntegration.sendReplyWithMentions(to, message, replyToId)

    fun decryptMedia(messageId: String) = waIntegration.decryptMedia(messageId)
}
