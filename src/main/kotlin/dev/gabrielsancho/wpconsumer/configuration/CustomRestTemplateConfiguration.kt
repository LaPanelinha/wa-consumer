package dev.gabrielsancho.wpconsumer.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class CustomRestTemplateConfiguration {

    @Value("\${wa.api.connection.timeout}")
    lateinit var connectionTimeout: Number

    @Value("\${wa.api.read.timeout}")
    lateinit var readTimout: Number

    @Bean
    @Primary
    fun getRestTemplate(builder: RestTemplateBuilder): RestTemplate = builder
            .setConnectTimeout(Duration.ofMillis(connectionTimeout.toLong()))
            .setReadTimeout(Duration.ofMillis(readTimout.toLong()))
            .build()

}