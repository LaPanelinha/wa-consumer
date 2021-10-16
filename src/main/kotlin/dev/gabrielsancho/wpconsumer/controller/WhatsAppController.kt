package dev.gabrielsancho.wpconsumer.controller

import dev.gabrielsancho.wpconsumer.controller.handler.MessageHandler
import dev.gabrielsancho.wpconsumer.domain.EventEnum
import dev.gabrielsancho.wpconsumer.domain.EventPayload
import dev.gabrielsancho.wpconsumer.extension.parseEventPayloadData
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
        val messageHandler: MessageHandler
) {

    @PostMapping("/webhook")
    fun webhook(@RequestBody eventPayload: EventPayload): ResponseEntity<Any> {

        if (eventPayload.event == EventEnum.MESSAGE)
            messageHandler.handleMessage(parseEventPayloadData(eventPayload))

        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping
    fun test() = ResponseEntity("OK", HttpStatus.OK)
}
