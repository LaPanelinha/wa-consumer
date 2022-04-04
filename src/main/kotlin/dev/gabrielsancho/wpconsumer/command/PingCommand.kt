package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PingCommand(
    @Value("\${wa.command.prefix}") val commandPrefix: String,
    val service: WhatsappService
) : Command<PingCommand.PingArguments>() {
    override val alias = CommandAlias.PING_COMMAND

    val possibleMessages = listOf(
        "Tô aqui meu bom \uD83D\uDC49\uD83E\uDD2D",
        "Tá querendo e não tá sabendo pedir \uD83C\uDF46\uD83D\uDE1B",
        "\uD83D\uDD95",
        "RAPAAAAAAAAAAAAAAAAIIIIIIZZ \uD83D\uDC2D\uD83D\uDC2D",
        "PAAAARE ✋",
        "Ai minha voida \uD83D\uDE46\uD83C\uDFFF\u200D♂️"
    )

    override fun execute(message: Message) {
        PingArguments().loadArguments(message.text)
        sendPong(message)
    }

    private fun sendPong(message: Message) {
        service.sendReplyWithMentions(message.from, possibleMessages.random(), message.id)
    }

    inner class PingArguments : CommandArguments(commandPrefix, alias)
}
