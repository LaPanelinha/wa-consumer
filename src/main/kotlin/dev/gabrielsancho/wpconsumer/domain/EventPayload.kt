package dev.gabrielsancho.wpconsumer.domain

data class EventPayload(
        val ts: Number,
        val sessionId: String,
        val id: String,
        val event: EventEnum,
        val data: Any
)
