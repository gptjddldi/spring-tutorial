package com.example.springtutorial.springdatatutorial.security

import com.example.springtutorial.springdatatutorial.model.User
import com.example.springtutorial.springdatatutorial.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthenticationFacade(
    private val userService: UserService
) {
    val authentication: Authentication
        get() =
            SecurityContextHolder.getContext().authentication
    val authenticatedUserEntity: User
        get() = authentication.principal.let {
            userService.getUserByEmail(it as String)
        }
}