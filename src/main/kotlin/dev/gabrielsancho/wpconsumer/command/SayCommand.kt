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
        val args = SayArguments(message.text)

        val argsText = args.text.joinToString(" ")

        service.sendText(message.from, argsText.ifBlank { possibleMessages.random() })
        service.react(message.id, "\uD83D\uDDE3️")
    }

    inner class SayArguments(arguments: String?) : CommandArguments(commandPrefix, alias, arguments) {

        @Parameter(description = "Texto a ser repetido")
        var text: MutableList<String> = mutableListOf()
    }
}
