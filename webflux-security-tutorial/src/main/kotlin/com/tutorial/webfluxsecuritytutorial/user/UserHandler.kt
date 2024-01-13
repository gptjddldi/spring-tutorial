package com.tutorial.webfluxsecuritytutorial.user

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler(private val userService: UserService) {
    fun getUsers(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(userService.getUsers(), User::class.java)
    }

    fun createUser(request: ServerRequest): Mono<ServerResponse> {
        val user = request.bodyToMono(User::class.java)
        return user.flatMap {
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.createUser(it), User::class.java)
        }
    }
}