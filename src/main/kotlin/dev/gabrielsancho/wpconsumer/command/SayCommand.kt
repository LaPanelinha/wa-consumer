package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.stereotype.Component

@Component
class SayCommand(
    private val service: WhatsappService
) : Command() {

    override val alias = CommandAlias.SAY_COMMAND

    val possibleMessages = listOf(
        "Tô aqui meu bom \uD83D\uDC49\uD83E\uDD2D",
        "Tá querendo e não tá sabendo pedir \uD83C\uDF46\uD83D\uDE1B",
        "\uD83D\uDD95",
        "RAPAAAAAAAAAAAAAAAAIIIIIIZZ \uD83D\uDC2D\uD83D\uDC2D",
        "PAAAARE ✋",
        "Ai minha voida \uD83D\uDE46\uD83C\uDFFF\u200D♂️"
    )

    override fun execute(message: Message) {
        val text = stringFromText(message.text)

        service.sendText(message.from, text ?: possibleMessages.random())
        service.react(message.id, "\uD83D\uDDE3️")
    }

    private fun stringFromText(text: String?): String? {
        text ?: return null
        val commandPrefix = text.split(" ")[0]

        return text.removePrefix(commandPrefix)
    }
}
