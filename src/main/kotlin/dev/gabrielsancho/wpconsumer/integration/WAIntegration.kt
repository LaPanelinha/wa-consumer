package dev.gabrielsancho.wpconsumer.integration

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.domain.StickerMetadata
import dev.gabrielsancho.wpconsumer.dto.whatsapp.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class WAIntegration(
        private val restTemplate: RestTemplate
) {
    @Value("\${wa.api.url}")
    lateinit var baseUrl: String

    fun sendText(to: String, message: String) =
            postForLocation("/sendText", SendTextDTO(to, message))

    fun reply(to: String, messageId: String, message: String, sendSeen: Boolean) =
            postForLocation("/reply", ReplyDTO(to, message, messageId, sendSeen))

    fun sendImageAsSticker(to: String, image: String, metadata: StickerMetadata? = null) =
            postForLocation("/sendImageAsSticker", ImageAsStickerDTO(to, image, metadata))

    fun sendStickerFromUrl(to: String, url: String, metadata: StickerMetadata? = null) =
            postForLocation("/sendImageAsSticker", StickerFromUrlDTO(to, url, metadata))

    fun getMessageById(id: String) = postForObject(
            "/getMessageById",
            MessageByIdDTO(id),
            typeReference<ResponseDTO<Message>>()
    )

    fun decryptMedia(id: String) = postForObject(
            "/decryptMedia",
            DecryptMediaDTO(id),
            typeReference<ResponseDTO<String>>()
    )

    private fun postForLocation(path: String, body: Any) {
        restTemplate.postForLocation("$baseUrl$path", ArgsDTO(body))
    }

    private fun <R : ResponseDTO<T>, T> postForObject(path: String, body: Any, responseType: ParameterizedTypeReference<R>): T? {
        val request = RequestEntity.post("$baseUrl$path").body(ArgsDTO(body))
        return restTemplate.exchange(request, responseType).body?.response
    }
}

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}
