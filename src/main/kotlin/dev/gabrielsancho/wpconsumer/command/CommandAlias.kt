package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.extension.safeCapitalize
import java.util.*

enum class CommandAlias(vararg val values: String) {
    COMMANDS_COMMAND("help", "h"),
    STICKER_COMMAND("sticker", "figurinha", "f"),
    PING_COMMAND("ping"),
    SAY_COMMAND("say");

    fun prettyName() = name
            .split("_")
            .dropLast(1)
            .joinToString(" ") { it.lowercase(Locale.getDefault()).safeCapitalize() }
}