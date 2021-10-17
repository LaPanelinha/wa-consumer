package dev.gabrielsancho.wpconsumer.extension

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.MessageType

val Message.text
    get() = when (type) {
        MessageType.IMAGE, MessageType.VIDEO -> caption
        MessageType.TEXT -> body
        MessageType.BUTTONS_RESPONSE -> selectedButtonId
        else -> null
    }
