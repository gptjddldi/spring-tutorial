package com.example.webfluxtutorial.controller

import com.example.webfluxtutorial.entity.User
import com.example.webfluxtutorial.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/api/users")
    fun getUsers(): Flux<User> {
        return userService.getUsers()
    }

//    @PostMapping("/api/user")
//    fun createUser(): Mono<User> {
//        return userService.createUser()
//    }
}