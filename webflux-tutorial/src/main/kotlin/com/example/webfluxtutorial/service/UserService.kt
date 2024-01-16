package com.example.webfluxtutorial.service

import com.example.webfluxtutorial.entity.User
import com.example.webfluxtutorial.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {
    @Autowired

    fun getUsers(): Flux<User> {
        return userRepository.findAll()
    }

    fun createUser(user: User): Mono<User> {
        return userRepository.save(user)
    }
}
