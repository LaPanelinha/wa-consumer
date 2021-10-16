package dev.gabrielsancho.wpconsumer.extension

import java.util.*

fun String.safeCapitalize() =
        replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }


fun String.toString(format: WaFormat) = "${format.value}$this${format.value}"

enum class WaFormat(val value: String) {
    ITALIC("_"),
    BOLD("*"),
    STRIKETHROUGH("~"),
    MONOSPACE("```")
}
