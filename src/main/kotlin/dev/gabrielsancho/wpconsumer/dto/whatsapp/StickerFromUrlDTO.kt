package dev.gabrielsancho.wpconsumer.dto.whatsapp

import dev.gabrielsancho.wpconsumer.domain.StickerMetadata

data class StickerFromUrlDTO(
        val to: String,
        val url: String,
        val stickerMetadata: StickerMetadata? = null
)
