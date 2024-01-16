package com.example.webfluxtutorial.security

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
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import reactor.core.publisher.Mono


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity, authenticationWebFilter: AuthenticationWebFilter): SecurityWebFilterChain {
        return http
            .csrf {
                it.disable()
            }
            .logout {
                it.disable()
            }
            .authorizeExchange{
                it
                .pathMatchers("/login")
                .permitAll()
                .anyExchange()
                .authenticated()
            }
            .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationWebFilter(reactiveAuthenticationManager: ReactiveAuthenticationManager,
                                reactiveAuthenticationConverter: CustomConverter
    ): AuthenticationWebFilter {
        val filter  = AuthenticationWebFilter(reactiveAuthenticationManager)

        filter.setSecurityContextRepository(WebSessionServerSecurityContextRepository())
        filter.setRequiresAuthenticationMatcher { ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login").matches(it) }
        filter.setServerAuthenticationConverter(reactiveAuthenticationConverter)
        filter.setAuthenticationSuccessHandler { webFilterExchange, authentication ->
            webFilterExchange.exchange.response.statusCode = HttpStatus.OK
            val a = webFilterExchange.exchange.response.bufferFactory().wrap("${authentication.name} hey!".toByteArray())
            webFilterExchange.exchange.response.writeWith(Mono.just(a))
        }

        return filter
    }

    @Bean
    fun reactiveAuthenticationManager(reactiveUserDetailsService: ReactiveUserDetailsService, passwordEncoder: PasswordEncoder): ReactiveAuthenticationManager {
        val manager = UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService)
        manager.setPasswordEncoder(passwordEncoder)

        return manager
    }
}
