package dev.gabrielsancho.wpconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WpConsumerApplication

fun main(args: Array<String>) {
    runApplication<WpConsumerApplication>(*args)
}
