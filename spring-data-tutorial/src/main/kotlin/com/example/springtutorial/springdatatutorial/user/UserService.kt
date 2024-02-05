package com.example.springtutorial.springdatatutorial.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
){
    fun getUsers(): Iterable<User> {
        return userRepository.findAll()
    }

    fun getUserById(id: Int): User {
        return userRepository.findById(id).get()
    }
    fun getUserByEmail(email: String): User {
        return userRepository.findUserByEmail(email)
    }

    fun addUser(u: UserSignupRequestDto): User {
        val user = u.toEntity(passwordEncoder)

        return userRepository.save(user)
    }
}