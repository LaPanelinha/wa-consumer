package dev.gabrielsancho.wpconsumer.dto.whatsapp

import dev.gabrielsancho.wpconsumer.domain.StickerMetadata

class ImageAsStickerDTO(
        val to: String,
        val image: String,
        stickerMetadataObj: StickerMetadata? = null
) {
    val stickerMetadata = StickerMetadataDTO(stickerMetadataObj)
}


