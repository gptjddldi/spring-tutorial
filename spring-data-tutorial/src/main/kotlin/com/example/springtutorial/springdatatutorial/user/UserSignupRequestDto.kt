package com.example.springtutorial.springdatatutorial.user

import org.springframework.security.crypto.password.PasswordEncoder

data class UserSignupRequestDto(
    val name: String,
    val email: String,
    val password: String
) {
    fun toEntity(passwordEncoder: PasswordEncoder): User {
        return User(
            id = 1,
            name = name,
            email = email,
            password = passwordEncoder.encode(password)
        )
    }
}