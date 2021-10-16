package dev.gabrielsancho.wpconsumer.command

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import dev.gabrielsancho.wpconsumer.exception.HelpRequestedException
import dev.gabrielsancho.wpconsumer.extension.usageString

abstract class CommandArguments(
        private val commandPrefix: String,
        private val commandAlias: CommandAlias
) {

    @Parameter(names = ["--help", "-h"], description = "Mostra essa ajuda", help = true)
    var helpRequested = false

    private fun getUsage() = JCommander.newBuilder()
            .addObject(this)
            .build().apply {
                setProgramName(
                        commandAlias.prettyName(),
                        *commandAlias.values.map { "$commandPrefix$it" }.toTypedArray()
                )
            }.usageString()

    fun loadArguments(text: String?): CommandArguments {
        val args = text?.split(" ")?.drop(1)?.toTypedArray() ?: arrayOf()
        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(*args)

        if (helpRequested) throw HelpRequestedException(getUsage())

        return this
    }
}
