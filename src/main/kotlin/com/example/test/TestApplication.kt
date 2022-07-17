package com.example.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class TestApplication

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}
