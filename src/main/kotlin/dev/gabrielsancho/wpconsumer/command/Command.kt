package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message


abstract class Command {
    abstract val alias: CommandAlias

    abstract fun execute(message: Message)
}
