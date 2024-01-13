package com.tutorial.webfluxsecuritytutorial.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import reactor.core.publisher.Mono


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity, sessionLoginFilter: AuthenticationWebFilter): SecurityWebFilterChain {

        return http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .httpBasic { it.disable() }
            .authorizeExchange{
                it
                    .pathMatchers("/login")
                    .permitAll()
                    .anyExchange()
                    .authenticated()
            }
            .addFilterAt(sessionLoginFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    @Bean
    fun serverSecurityContextRepository(): ServerSecurityContextRepository? {
        return WebSessionServerSecurityContextRepository()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun sessionLoginFilter(
        sessionAuthenticationManager: ReactiveAuthenticationManager,
        serverSecurityContextRepository: ServerSecurityContextRepository,
        customConverter: ServerAuthenticationConverter
    ) : AuthenticationWebFilter {
        val filter = AuthenticationWebFilter(sessionAuthenticationManager)

        filter.setSecurityContextRepository(serverSecurityContextRepository)
        filter.setRequiresAuthenticationMatcher { ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login").matches(it) }
        filter.setServerAuthenticationConverter(customConverter)
        filter.setAuthenticationSuccessHandler { webFilterExchange, authentication ->
            webFilterExchange.exchange.response.statusCode = HttpStatus.OK
            Mono.empty()
        }

        return filter
    }

    @Bean
    fun sessionAuthenticationManager(
        customUserDetailsService: CustomUserDetailsService,
        passwordEncoder: PasswordEncoder
    ): ReactiveAuthenticationManager {
        val manager = UserDetailsRepositoryReactiveAuthenticationManager(customUserDetailsService)
        manager.setPasswordEncoder(passwordEncoder)
        return manager
    }

}