package com.tutorial.webfluxsecuritytutorial.repository

import com.tutorial.webfluxsecuritytutorial.model.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository: R2dbcRepository<User, Long> {
    fun findByName(name: String): Mono<User?>
}