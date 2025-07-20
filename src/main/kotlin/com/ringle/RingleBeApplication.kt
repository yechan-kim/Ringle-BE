package com.ringle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class RingleBeApplication

fun main(args: Array<String>) {
    runApplication<RingleBeApplication>(*args)
}
