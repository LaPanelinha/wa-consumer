package dev.gabrielsancho.wpconsumer.service

import dev.gabrielsancho.wpconsumer.domain.StickerMetadata
import dev.gabrielsancho.wpconsumer.dto.whatsapp.SimulateTypingDTO
import dev.gabrielsancho.wpconsumer.integration.WAIntegration
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class WhatsappService(
    private val waIntegration: WAIntegration
) {
    @Async
    fun sendText(to: String, message: String) = waIntegration.sendText(to, message)

    @Async
    fun reply(to: String, messageId: String, message: String) = waIntegration.reply(to, messageId, message, true)

    @Async
    fun sendImageAsSticker(to: String, image: String, metadata: StickerMetadata?) =
        waIntegration.sendImageAsSticker(to, image, metadata)

    @Async
    fun sendStickerFromUrl(to: String, url: String, metadata: StickerMetadata?) =
        waIntegration.sendStickerFromUrl(to, url, metadata)

    @Async
    fun sendMp4AsSticker(to: String, mp4: String, metadata: StickerMetadata) =
        waIntegration.sendMp4AsSticker(to, mp4, metadata)

    @Async
    fun getMessageById(messageId: String) = waIntegration.getMessageById(messageId)

    @Async
    fun simulateTyping(to: String, on: Boolean) = waIntegration.simulateTyping(SimulateTypingDTO(to, on))

    @Async
    fun react(messageId: String, emoji: String) = waIntegration.react(messageId, emoji)

    @Async
    fun sendReplyWithMentions(to: String, message: String, replyToId: String) =
        waIntegration.sendReplyWithMentions(to, message, replyToId)

    fun decryptMedia(messageId: String) = waIntegration.decryptMedia(messageId)
}
