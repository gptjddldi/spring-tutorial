package com.tutorial.webfluxsecuritytutorial.user

import com.tutorial.webfluxsecuritytutorial.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun getUsers(): Flux<User> {
        return userRepository.findAll()
    }
    fun createUser(user: User): Mono<User> {
        val u = User(
            id= user.id,
            name= user.name,
            password= passwordEncoder.encode(user.password),
        )
        return userRepository.save(u)
    }
}