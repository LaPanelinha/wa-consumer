package dev.gabrielsancho.wpconsumer.facade

import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.dto.whatsapp.ArgsDTO
import dev.gabrielsancho.wpconsumer.dto.whatsapp.DecryptMediaDTO
import dev.gabrielsancho.wpconsumer.dto.whatsapp.ResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class WaDecryptIntegrationFacade(
        private val restTemplate: RestTemplate
) {
    @Value("\${wa.decrypt.url}")
    lateinit var baseUrl: String

    fun decryptMedia(message: Message): String? {
        val deprecatedMms3Url = message.deprecatedMms3Url ?: return null
        val mediaKey = message.mediaKey ?: return null
        val filehash = message.filehash ?: return null
        val mimetype = message.mimetype ?: return null
        val type = message.type.value
        val size = message.size ?: return null

        val dto = DecryptMediaDTO(deprecatedMms3Url, mediaKey, filehash, mimetype, type, size)

        val response = postForObject("/decrypt", dto, typeReference<ResponseDTO<String>>())
        return if (response.isNullOrBlank()) null else response
    }

    private fun <R : ResponseDTO<T>, T> postForObject(path: String, body: Any, responseType: ParameterizedTypeReference<R>): T? {
        val request = RequestEntity.post("$baseUrl$path").body(ArgsDTO(body))
        return restTemplate.exchange(request, responseType).body?.response
    }
}
