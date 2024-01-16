package com.example.webfluxtutorial.security

import com.example.webfluxtutorial.repository.UserRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ReactiveUserDetailsService(private val userRepository: UserRepository ): ReactiveUserDetailsService {
    override fun findByUsername(username: String): Mono<UserDetails> {
        return userRepository
            .findByName(username)
            .map {u ->
                User.withUsername(u!!.name).password(u.password).roles("USER1").build()
            }
            .switchIfEmpty(Mono.empty())
    }
}
