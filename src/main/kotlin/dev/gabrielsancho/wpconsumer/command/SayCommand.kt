package dev.gabrielsancho.wpconsumer.command

import com.beust.jcommander.Parameter
import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SayCommand(
        @Value("\${wa.command.prefix}") val commandPrefix: String,
        private val service: WhatsappService
) : Command<SayCommand.SayArguments>() {

    override val alias = CommandAlias.SAY_COMMAND

    override fun execute(message: Message) {
        val args = SayArguments().apply { loadArguments(message.text) }

        service.sendText(message.from, args.text.joinToString(" "))
    }

    inner class SayArguments : CommandArguments(commandPrefix, alias) {

        @Parameter(description = "Texto a ser repetido")
        var text: MutableList<String> = mutableListOf()
    }
}
