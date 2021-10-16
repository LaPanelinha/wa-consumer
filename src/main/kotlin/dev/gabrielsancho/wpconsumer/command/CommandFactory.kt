package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.exception.CommandNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class CommandFactory(
        private val commands: List<Command<*>>
) {
    @Value("\${wa.command.prefix}")
    lateinit var commandPrefix: String

    val commandAliasesCache = mutableMapOf<String, Command<*>>()

    @PostConstruct
    fun initCommandCache() {
        commands.forEach { command ->
            command.alias.values.forEach { alias ->
                commandAliasesCache[alias] = command
            }
        }
    }

    fun getCommand(text: String): Command<*> {
        val alias = getAliasFromText(text)
        return commandAliasesCache.getOrElse(alias) { throw CommandNotFoundException() }
    }

    private fun getAliasFromText(text: String): String {
        if (!text.startsWith(commandPrefix)) throw CommandNotFoundException()

        return text.removePrefix(commandPrefix).substringBefore(" ")
    }

    fun isCommand(text: String) =
            text.startsWith(commandPrefix) &&
                    text.removePrefix(commandPrefix).getOrElse(0) { ' ' } != ' '
}
