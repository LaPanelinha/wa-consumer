package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.stereotype.Component


@Component
class AllCommand(
    val service: WhatsappService
) : Command() {
    override val alias = CommandAlias.ALL_COMMAND

    override fun execute(message: Message) {

        val participants = message.chat.groupMetadata?.participants?.joinToString(" ") {
            val number = it.id.replace("@c.us", "")
            return@joinToString "@$number"
        } ?: message.from

        service.sendReplyWithMentions(message.chatId, participants, message.id)
        service.react(message.id, "\uD83D\uDCE3️")
    }
}
