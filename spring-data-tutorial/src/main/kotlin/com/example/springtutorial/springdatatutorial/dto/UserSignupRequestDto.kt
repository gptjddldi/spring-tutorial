package com.example.springtutorial.springdatatutorial.dto

import com.example.springtutorial.springdatatutorial.model.User
import org.springframework.security.crypto.password.PasswordEncoder

data class UserSignupRequestDto(
    val name: String,
    val email: String,
    val password: String
) {
    fun toEntity(passwordEncoder: PasswordEncoder): User {
        return User(
            name = name,
            email = email,
            password = passwordEncoder.encode(password)
        )
    }
}