package dev.gabrielsancho.wpconsumer.command

import com.beust.jcommander.Parameter
import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ReactCommand(
    @Value("\${wa.command.prefix}") val commandPrefix: String,
    val service: WhatsappService
) : Command() {
    override val alias = CommandAlias.REACT_COMMAND


    override fun execute(message: Message) {
        val args = ReactArguments(message.text)

        service.react(message.quotedMsg?.id ?: message.id, args.emoji ?: return)
    }

    inner class ReactArguments(arguments: String?) : CommandArguments(commandPrefix, alias, arguments) {

        @Parameter(description = "Emoji pra reagir")
        var emoji: String? = null
    }
}
