package dev.gabrielsancho.wpconsumer.domain

import com.fasterxml.jackson.annotation.JsonProperty

enum class MessageType(val value: String) {
    @JsonProperty("audio")
    AUDIO("audio"),

    @JsonProperty("buttons_response")
    BUTTONS_RESPONSE("buttons_response"),

    @JsonProperty("vcard")
    CONTACT_CARD("vcard"),

    @JsonProperty("multi_vcard")
    CONTACT_CARD_MULTI("multi_vcard"),

    @JsonProperty("document")
    DOCUMENT("document"),

    @JsonProperty("image")
    IMAGE("image"),

    @JsonProperty("list_response")
    LIST_RESPONSE("list_response"),

    @JsonProperty("location")
    LOCATION("location"),

    @JsonProperty("order")
    ORDER("order"),

    @JsonProperty("revoked")
    REVOKED("revoked"),

    @JsonProperty("sticker")
    STICKER("sticker"),

    @JsonProperty("chat")
    TEXT("chat"),

    @JsonProperty("unknown")
    UNKNOWN("unknown"),

    @JsonProperty("video")
    VIDEO("video"),

    @JsonProperty("ptt")
    VOICE("ptt")
}