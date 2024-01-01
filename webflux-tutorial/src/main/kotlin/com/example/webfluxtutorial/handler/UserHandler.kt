package com.example.webfluxtutorial.handler

import com.example.webfluxtutorial.dto.UserDto
import com.example.webfluxtutorial.entity.User
import com.example.webfluxtutorial.service.UserService
import org.springframework.http.HttpStatus
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
        val user = request.bodyToMono(UserDto::class.java)
        return user
            .flatMap {
                ServerResponse
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userService.createUser(it), User::class.java)
            }
    }
}