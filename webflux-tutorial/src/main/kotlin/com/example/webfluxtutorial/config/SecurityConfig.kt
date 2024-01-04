package com.example.webfluxtutorial.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.csrf { obj: ServerHttpSecurity.CsrfSpec? -> obj?.disable() }.authorizeExchange{
            it
                .pathMatchers("/handler/auth/register/**")
                .permitAll()
                .anyExchange()
                .authenticated()

        }

        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun userDetailService(): MapReactiveUserDetailsService {
        val user: UserDetails = User
            .withUsername("user")
            .password("password")
            .roles("USER")
            .build()
        return MapReactiveUserDetailsService(user)
    }
}