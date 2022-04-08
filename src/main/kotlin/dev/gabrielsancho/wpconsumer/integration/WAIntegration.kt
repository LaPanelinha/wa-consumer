package dev.gabrielsancho.wpconsumer.integration

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.StickerMetadata
import dev.gabrielsancho.wpconsumer.dto.whatsapp.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Component
class WAIntegration(
    private val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(WAIntegration::class.java)

    @Value("\${wa.api.url}")
    lateinit var baseUrl: String

    @Value("\${wa.api.key}")
    lateinit var apiKey: String

    private val httpHeaders: HttpHeaders
        get() = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            add("api_key", apiKey)
        }

    fun sendText(to: String, message: String) =
        postForLocation("/sendText", SendTextDTO(to, message))

    fun reply(to: String, messageId: String, message: String, sendSeen: Boolean) =
        postForLocation("/reply", ReplyDTO(to, message, messageId, sendSeen))

    fun sendImageAsSticker(to: String, image: String, metadata: StickerMetadata? = null) =
        postForLocation("/sendImageAsSticker", ImageAsStickerDTO(to, image, metadata))

    fun sendStickerFromUrl(to: String, url: String, metadata: StickerMetadata? = null) =
        postForLocation("/sendImageAsSticker", StickerFromUrlDTO(to, url, metadata))

    fun sendMp4AsSticker(to: String, mp4: String, metadata: StickerMetadata) =
        postForLocation("/sendMp4AsSticker", Mp4AsStickerDTO(to, mp4, metadata))

    fun react(messageId: String, emoji: String) =
        postForLocation("/react", ReactDTO(messageId, emoji))

    fun getMessageById(id: String) = postForObject(
        "/getMessageById",
        MessageByIdDTO(id),
        typeReference<ResponseDTO<Message>>()
    )

    fun sendReplyWithMentions(to: String, content: String, replyToId: String) =
        postForLocation("/sendReplyWithMentions", ReplyWithMentionsDTO(to, content, replyToId))

    fun decryptMedia(id: String) = postForObject(
        "/decryptMedia",
        DecryptMediaDTO(id),
        typeReference<ResponseDTO<String>>()
    )

    fun simulateTyping(simulateTypingDTO: SimulateTypingDTO) =
        postForLocation("/simulateTyping", simulateTypingDTO)

    fun postForLocation(path: String, body: Any): CompletableFuture<*> {
        logger.info("Sending[$path]: $body")
        val requestEntity = HttpEntity(ArgsDTO(body), httpHeaders)

        restTemplate.postForLocation("$baseUrl$path", requestEntity)

        return CompletableFuture.completedFuture(null)
    }

    fun <R : ResponseDTO<T>, T> postForObject(
        path: String,
        body: Any,
        responseType: ParameterizedTypeReference<R>
    ): CompletableFuture<T?> {
        logger.info("Sending[$path]: $body")

        println("Sending: $path")
        val request = RequestEntity
            .post("$baseUrl$path")
            .headers(httpHeaders)
            .body(ArgsDTO(body))

        val response = restTemplate.exchange(request, responseType).body?.response

        return CompletableFuture.completedFuture(response)
    }
}

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}
