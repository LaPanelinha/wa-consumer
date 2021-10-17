package dev.gabrielsancho.wpconsumer.dto.whatsapp

import dev.gabrielsancho.wpconsumer.domain.StickerMetadata

class StickerMetadataDTO(stickerMetadata: StickerMetadata?) {
    val author = stickerMetadata?.author
    val cropPosition = stickerMetadata?.cropPosition?.value
    val keepScale = stickerMetadata?.keepScale
    val pack = stickerMetadata?.pack
    val circle = stickerMetadata?.circle
}
