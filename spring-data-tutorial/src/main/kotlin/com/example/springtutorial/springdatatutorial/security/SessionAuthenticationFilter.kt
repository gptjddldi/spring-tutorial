package com.example.springtutorial.springdatatutorial.security

import com.example.springtutorial.springdatatutorial.dto.security.LoginRequestDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class SessionAuthenticationFilter(
    private val sessionAuthenticationManager: SessionAuthenticationManager
): UsernamePasswordAuthenticationFilter(sessionAuthenticationManager) {
    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse?
    ): Authentication {
        val mapper = jacksonObjectMapper()

        val creds = mapper.readValue<LoginRequestDto>(req.inputStream)

        return sessionAuthenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                creds.email,
                creds.password
            )
        )
    }
}