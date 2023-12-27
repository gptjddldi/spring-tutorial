package com.example.webfluxtutorial.service

import com.example.webfluxtutorial.entity.User
import com.example.webfluxtutorial.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService( private val userRepository: UserRepository) {
    fun getUsers(): Flux<User> {
        return userRepository.findAll()
    }

    fun createUser(entry: User): Mono<User> {
        return userRepository.save(entry)
    }
}