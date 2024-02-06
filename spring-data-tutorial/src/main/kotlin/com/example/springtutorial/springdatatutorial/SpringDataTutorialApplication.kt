package com.example.springtutorial.springdatatutorial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@EnableJpaAuditing
@SpringBootApplication
class SpringDataTutorialApplication

fun main(args: Array<String>) {
	runApplication<SpringDataTutorialApplication>(*args)
}
