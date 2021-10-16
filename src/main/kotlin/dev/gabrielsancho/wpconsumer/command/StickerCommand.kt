package dev.gabrielsancho.wpconsumer.command

import com.beust.jcommander.Parameter
import dev.gabrielsancho.wpconsumer.domain.CropPosition
import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.MessageType
import dev.gabrielsancho.wpconsumer.domain.StickerMetadata
import dev.gabrielsancho.wpconsumer.extension.text
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.MalformedURLException
import java.net.URL

@Component
class StickerCommand(
        @Value("\${wa.command.prefix}") val commandPrefix: String,
        private val service: WhatsappService
) : Command<StickerCommand.StickerArguments>() {
    override val alias = CommandAlias.STICKER_COMMAND

    override fun execute(message: Message) {
        val args = StickerArguments().apply { loadArguments(message.text) }

        val url = args.url
        val metadata = metadataFromArgs(args)

        when (message.type) {
            MessageType.IMAGE -> sendImage(message, metadata)
            MessageType.TEXT -> {
                if (url != null && isValidUrl(args.url))
                    sendUrl(message, url, metadata)
                if (hasQuotedImage(message))
                    sendQuotedMessage(message, message.quotedMsgObj!!, metadata)
            }
            else -> Unit
        }
    }

    private fun sendUrl(message: Message, url: String, metadata: StickerMetadata) {
        service.sendStickerFromUrl(message.from, url, metadata)
    }

    private fun isValidUrl(str: String?) = try {
        URL(str)
        true
    } catch (ex: MalformedURLException) {
        false
    }

    private fun hasQuotedImage(message: Message) =
            message.quotedMsgObj != null && message.quotedMsgObj?.type == MessageType.IMAGE

    private fun sendImage(message: Message, metadata: StickerMetadata) {
        val body = service.decryptMedia(message) ?: message.body
        service.sendImageAsSticker(message.from, body, metadata)
    }

    private fun sendQuotedMessage(message: Message, quoted: Message, metadata: StickerMetadata) {
        val body = service.decryptMedia(quoted) ?: quoted.body
        service.sendImageAsSticker(message.from, body, metadata)
    }

    private fun metadataFromArgs(args: StickerCommand.StickerArguments) = StickerMetadata(
            args.author,
            CropPosition.fromString(args.cropPosition),
            args.keepScale,
            args.pack,
            args.circle
    )

    inner class StickerArguments : CommandArguments(commandPrefix, alias) {
        @Parameter(description = "Url da imagem")
        var url: String? = null

        @Parameter(names = ["--author", "-a"], description = "Autor da figurinha")
        var author: String? = "Voldemort"

        @Parameter(names = ["--crop-position", "-c"], description = "Posição para cortar a figurinha (top, right top, right, right bottom, bottom, left bottom, left, left top, center)")
        var cropPosition: String? = null

        @Parameter(names = ["--keep-scale", "-k"], description = "Mantem a escala da figurinha")
        var keepScale: Boolean = false

        @Parameter(names = ["--pack", "-p"], description = "Nome do pacote de figurinhas")
        var pack: String? = "Voldemort"

        @Parameter(names = ["--circle", "-ci"], description = "Figurinha circular")
        var circle: Boolean? = false
    }
}
