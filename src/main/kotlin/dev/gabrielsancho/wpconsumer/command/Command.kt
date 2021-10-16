package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message


abstract class Command<out T : CommandArguments> {
    abstract val alias: CommandAlias

    abstract fun execute(message: Message)
}
