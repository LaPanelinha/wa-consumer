package dev.gabrielsancho.wpconsumer.dto.whatsapp

data class ReplyDTO(
        val to: String,
        val content: String,
        val quotedMsgId: String,
        val sendSeen: Boolean
)
