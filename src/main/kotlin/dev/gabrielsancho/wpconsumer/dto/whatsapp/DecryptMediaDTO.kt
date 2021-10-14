package dev.gabrielsancho.wpconsumer.dto.whatsapp

data class DecryptMediaDTO(
        val deprecatedMms3Url: String,
        val mediaKey: String,
        val filehash: String,
        val mimetype: String,
        val type: String,
        val size: Long
)
