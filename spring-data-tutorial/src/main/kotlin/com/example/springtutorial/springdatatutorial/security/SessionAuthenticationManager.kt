package com.example.springtutorial.springdatatutorial.security

import com.example.springtutorial.springdatatutorial.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SessionAuthenticationManager(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()
        val user = userRepository.findUserByEmail(username)
        if (user != null && passwordEncoder.matches(password, user.password)) {
            val authorities = mutableListOf<GrantedAuthority>()
            return UsernamePasswordAuthenticationToken(user.email, user.password, authorities)
        } else {
            throw Exception("Invalid Credentials")
        }
    }
}