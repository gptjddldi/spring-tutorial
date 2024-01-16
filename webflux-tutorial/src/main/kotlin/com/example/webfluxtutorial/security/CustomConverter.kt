package com.example.webfluxtutorial.security

import org.springframework.core.ResolvableType
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class CustomConverter: ServerAuthenticationConverter {
    private val decoder = Jackson2JsonDecoder()

    override fun convert(exchange: ServerWebExchange): Mono<Authentication> {
        val elementType: ResolvableType = ResolvableType.forClass(LoginData::class.java)

        return exchange.request.body
            .next()
            .flatMap { buffer ->
                Mono.justOrEmpty(decoder.decode(buffer, elementType, null, null)).cast(LoginData::class.java)
            }
            .switchIfEmpty(Mono.error(UsernameNotFoundException("username not found")))
            .map {
                UsernamePasswordAuthenticationToken(it.name, it.password)
            }
    }
}
private data class LoginData (val name: String, val password: String)
