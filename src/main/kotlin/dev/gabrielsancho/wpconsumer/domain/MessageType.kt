package dev.gabrielsancho.wpconsumer.domain

import com.fasterxml.jackson.annotation.JsonProperty

enum class MessageType {
    @JsonProperty("audio")
    AUDIO,

    @JsonProperty("buttons_response")
    BUTTONS_RESPONSE,

    @JsonProperty("vcard")
    CONTACT_CARD,

    @JsonProperty("multi_vcard")
    CONTACT_CARD_MULTI,

    @JsonProperty("document")
    DOCUMENT,

    @JsonProperty("image")
    IMAGE,

    @JsonProperty("list_response")
    LIST_RESPONSE,

    @JsonProperty("location")
    LOCATION,

    @JsonProperty("order")
    ORDER,

    @JsonProperty("revoked")
    REVOKED,

    @JsonProperty("sticker")
    STICKER,

    @JsonProperty("chat")
    TEXT,

    @JsonProperty("unknown")
    UNKNOWN,

    @JsonProperty("video")
    VIDEO,

    @JsonProperty("ptt")
    VOICE
}