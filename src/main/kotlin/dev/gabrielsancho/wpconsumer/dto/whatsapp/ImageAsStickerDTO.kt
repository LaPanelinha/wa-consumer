package dev.gabrielsancho.wpconsumer.dto.whatsapp

import dev.gabrielsancho.wpconsumer.domain.StickerMetadata

class ImageAsStickerDTO(
        val to: String,
        val image: String,
        stickerMetadataObj: StickerMetadata? = null
) {
    val stickerMetadata = StickerMetadataDTO(stickerMetadataObj)
}

class StickerMetadataDTO(stickerMetadata: StickerMetadata?) {
    val author = stickerMetadata?.author
    val cropPosition = stickerMetadata?.cropPosition?.value
    val keepScale = stickerMetadata?.keepScale
    val pack = stickerMetadata?.pack
    val circle = stickerMetadata?.circle
}
