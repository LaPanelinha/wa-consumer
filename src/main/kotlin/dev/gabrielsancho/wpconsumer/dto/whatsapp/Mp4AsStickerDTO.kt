package dev.gabrielsancho.wpconsumer.dto.whatsapp

import dev.gabrielsancho.wpconsumer.domain.StickerMetadata

class Mp4AsStickerDTO(
        val to: String,
        val file: String,
        stickerMetadataObj: StickerMetadata? = null
) {
    val stickerMetadata = StickerMetadataDTO(stickerMetadataObj)
}

