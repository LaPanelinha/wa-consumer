package dev.gabrielsancho.wpconsumer.extension

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import dev.gabrielsancho.wpconsumer.domain.EventPayload

inline fun <reified T> parseEventPayloadData(eventPayload: EventPayload): T =
        ObjectMapper().convertValue(eventPayload.data, object : TypeReference<T>() {})
