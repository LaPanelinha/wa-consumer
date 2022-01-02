package dev.gabrielsancho.wpconsumer.dto.whatsapp

data class ReplyWithMentionsDTO(
    val to: String,
    val content: String,
    val replyMessageId: String
)