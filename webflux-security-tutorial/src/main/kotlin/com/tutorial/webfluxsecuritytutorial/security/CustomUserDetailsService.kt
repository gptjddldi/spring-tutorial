package com.tutorial.webfluxsecuritytutorial.security

import com.tutorial.webfluxsecuritytutorial.repository.UserRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : ReactiveUserDetailsService {
    override fun findByUsername(username: String): Mono<UserDetails?> {
        return userRepository
            .findByName(username)
            .map {u ->
                User.withUserDetails(CustomUserDetails(u!!)).build()
            }
            .switchIfEmpty(Mono.empty())
    }
}