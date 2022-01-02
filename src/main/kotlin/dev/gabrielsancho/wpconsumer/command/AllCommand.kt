package dev.gabrielsancho.wpconsumer.command

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AllCommand(
    @Value("\${wa.command.prefix}") val commandPrefix: String,
    val service: WhatsappService
) : Command<AllCommand.AllArguments>() {
    override val alias = CommandAlias.ALL_COMMAND

    override fun execute(message: Message) {
        val participants = message.chat.groupMetadata?.participants?.joinToString(" ") {
            val number = it.id.replace("@c.us", "")
            return@joinToString "@$number"
        } ?: return

        service.sendReplyWithMentions(message.chatId, participants, message.id)
    }

    inner class AllArguments : CommandArguments(commandPrefix, alias)
}