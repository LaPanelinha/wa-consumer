package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.exception.CommandNotFoundException
import java.util.*

enum class CommandType(vararg val aliases: String) {
    HELP("help", "ajuda", "h"),
    STICKER("sticker", "figurinha", "f"),
    PING("ping", "p"),
    MESSAGE_TO("message", "m"),
    SAY("say", "s");

    companion object {
        fun fromAlias(alias: String): CommandType =
                values().find { it.aliases.contains(alias.lowercase(Locale.getDefault())) }
                        ?: throw CommandNotFoundException()
    }
}
