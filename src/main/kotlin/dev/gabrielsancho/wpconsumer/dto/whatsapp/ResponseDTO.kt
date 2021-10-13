package dev.gabrielsancho.wpconsumer.dto.whatsapp

data class ResponseDTO<T>(
        val success: Boolean,
        val response: T
)
