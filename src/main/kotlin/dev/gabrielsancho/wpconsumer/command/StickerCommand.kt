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
            MessageType.IMAGE, MessageType.VIDEO ->
                sendMediaSticker(message.from, message, metadata)
            MessageType.TEXT -> {
                if (hasQuotedMedia(message))
                    sendMediaSticker(message.from, message.quotedMsgObj!!, metadata)

                if (url != null)
                    sendUrlSticker(message.from, url, metadata)
            }
            else -> Unit
        }
    }

    private fun sendMediaSticker(to: String, message: Message, metadata: StickerMetadata) {
        val body = service.decryptMedia(message.id) ?: message.body

        if (message.type == MessageType.VIDEO)
            service.sendMp4AsSticker(to, body, metadata)
        if (message.type == MessageType.IMAGE)
            service.sendImageAsSticker(to, body, metadata)
    }

    private fun sendUrlSticker(to: String, url: String, metadata: StickerMetadata) {
        if (isValidUrl(url))
            service.sendStickerFromUrl(to, url, metadata)
    }

    private fun isValidUrl(str: String?) = try {
        URL(str)
        true
    } catch (ex: MalformedURLException) {
        false
    }

    private fun hasQuotedMedia(message: Message) =
            message.quotedMsgObj != null &&
                    (message.quotedMsgObj?.type == MessageType.IMAGE ||
                            message.quotedMsgObj?.type == MessageType.VIDEO)

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
