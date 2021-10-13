package dev.gabrielsancho.wpconsumer.command

abstract class Command {
    abstract fun execute()
    abstract fun canExecute(): Boolean
    abstract fun getCantExecuteMessage(): String
}
