package com.example.webfluxtutorial.service

import com.example.webfluxtutorial.dto.UserDto
import com.example.webfluxtutorial.entity.User
import com.example.webfluxtutorial.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {
    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    fun getUsers(): Flux<User> {
        return userRepository.findAll()
    }

    fun createUser(userDto: UserDto): Mono<User> {
        val user = User(
            id = userDto.id,
            name = userDto.name,
            password = passwordEncoder!!.encode(userDto.password)
        )
        return userRepository.save(user)
    }
}
