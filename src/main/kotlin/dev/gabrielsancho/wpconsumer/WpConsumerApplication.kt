package dev.gabrielsancho.wpconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@SpringBootApplication
@EnableAsync
class WpConsumerApplication {
    @Bean
    fun asyncExecutor(): Executor = ThreadPoolTaskExecutor().apply {
        corePoolSize = 4
        maxPoolSize = 4
        setQueueCapacity(500)
        setThreadNamePrefix("wa-integration")
        initialize()
    }
}

fun main(args: Array<String>) {
    runApplication<WpConsumerApplication>(*args)
}
