package dev.gabrielsancho.wpconsumer.command

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import dev.gabrielsancho.wpconsumer.domain.CropPosition
import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.MessageType
import dev.gabrielsancho.wpconsumer.domain.StickerMetadata
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import java.net.MalformedURLException
import java.net.URL

class StickerCommand(
        private val message: Message,
        private val args: List<String>,
        private val service: WhatsappService
) : Command() {
    override fun execute() {
        when (message.type) {
            MessageType.IMAGE -> sendImage(message)
            MessageType.TEXT -> {
                val firstArg = args.getOrElse(0) { "" }
                if (isValidUrl(firstArg))
                    sendUrl(message, firstArg)
                if (hasQuotedImage(message))
                    sendQuotedMessage(message, message.quotedMsgObj!!)
            }
            else -> {
            }
        }
    }

    private fun isValidUrl(str: String?) = try {
        URL(str)
        true
    } catch (ex: MalformedURLException) {
        false
    }

    private fun hasQuotedImage(message: Message) =
            message.quotedMsgObj != null && message.quotedMsgObj?.type == MessageType.IMAGE

    private fun sendImage(message: Message) {
        val body = service.decryptMedia(message) ?: message.body
        service.sendImageAsSticker(message.from, body, metadataFromArgs())
    }

    private fun sendQuotedMessage(message: Message, quoted: Message) {
        val body = service.decryptMedia(quoted) ?: quoted.body
        service.sendImageAsSticker(message.from, body, metadataFromArgs())
    }

    private fun sendUrl(message: Message, url: String) {
        service.sendStickerFromUrl(message.from, url, metadataFromArgs())
    }

    override fun canExecute(): Boolean {
        if (message.type == MessageType.TEXT)
            return isValidUrl(args.getOrElse(0) { "" }) || hasQuotedImage(message)
        return message.type == MessageType.IMAGE
    }

    private fun metadataFromArgs(): StickerMetadata {
        val argsObject = ArgsObject()
        JCommander.newBuilder()
                .addObject(argsObject)
                .build()
                .parse(*args.toTypedArray())
        return argsObject.createStickerMetadata()
    }

    override fun getCantExecuteMessage() = "Imagem inválida."

    class ArgsObject {
        @Parameter(names = ["-a", "--author"], description = "Autor da figurinha ( default: Voldemort' )")
        var author: String? = "Voldemort"

        @Parameter(names = ["-r", "--crop-position"], description = "Posição para cortar a figurinha ( default: null )")
        var cropPosition: String? = null

        @Parameter(names = ["-k", "--keep-scale"], description = "Mantém a escala da figurinha ( default: false )")
        var keepScale: Boolean = false

        @Parameter(names = ["-p", "--pack"], description = "Nome do pacote de figurinhas ( default: Voldemort )")
        var pack: String? = "Voldemort"

        @Parameter(names = ["-c", "--circle"], description = "Figurinha circular ( default: false )")
        var circle: Boolean? = false

        fun createStickerMetadata() = StickerMetadata(author, CropPosition.fromString(cropPosition), keepScale, pack, circle)
    }
}
