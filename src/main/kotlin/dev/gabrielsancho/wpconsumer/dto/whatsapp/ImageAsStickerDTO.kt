package dev.gabrielsancho.wpconsumer.dto.whatsapp

import dev.gabrielsancho.wpconsumer.domain.StickerMetadata

data class ImageAsStickerDTO(
        val to: String,
        val image: String,
        val stickerMetadata: StickerMetadata? = null
)
