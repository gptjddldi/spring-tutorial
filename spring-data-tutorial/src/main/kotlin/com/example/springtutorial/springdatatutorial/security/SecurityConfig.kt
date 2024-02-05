package com.example.springtutorial.springdatatutorial.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.context.HttpSessionSecurityContextRepository

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val sessionAuthenticationManager: SessionAuthenticationManager
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .logout { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/login", "/error", "/users")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .addFilterAt(sessionAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun sessionAuthenticationFilter(): SessionAuthenticationFilter {
       val customFilter = SessionAuthenticationFilter(sessionAuthenticationManager)
        customFilter.usernameParameter = "email"
        customFilter.setRequiresAuthenticationRequestMatcher { it.method == "POST" && it.requestURI == "/api/login" }
        customFilter.setSecurityContextRepository(HttpSessionSecurityContextRepository())
        customFilter.setAuthenticationSuccessHandler { _, response, authentication ->
            response.status = 200
            response.writer.write("hey, ${authentication.name}")
        }
        return customFilter
    }
}