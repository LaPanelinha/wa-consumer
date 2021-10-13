package dev.gabrielsancho.wpconsumer.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import dev.gabrielsancho.wpconsumer.domain.EventEnum
import dev.gabrielsancho.wpconsumer.domain.EventPayload
import dev.gabrielsancho.wpconsumer.domain.Message
import dev.gabrielsancho.wpconsumer.service.WhatsappService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/wa")
class WhatsAppController(
        val service: WhatsappService
) {

    @PostMapping("/webhook")
    fun webhook(@RequestBody eventPayload: EventPayload): ResponseEntity<Any> {

        when (eventPayload.event) {
            EventEnum.MESSAGE -> onMessage(eventPayload)
            else -> {
            }
        }

        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping()
    fun test() = ResponseEntity("OK", HttpStatus.OK)

    private fun onMessage(eventPayload: EventPayload) {
        val message = ObjectMapper().convertValue(eventPayload.data, object : TypeReference<Message>() {})
        service.handleMessage(message)
    }
}
