package com.example.springtutorial.springdatatutorial.controller

import com.example.springtutorial.springdatatutorial.model.User
import com.example.springtutorial.springdatatutorial.service.UserService
import com.example.springtutorial.springdatatutorial.dto.UserSignupRequestDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(private val userService: UserService) {

    @GetMapping("/users")
    fun getUsers(): Iterable<User> {
        return userService.getUsers()
    }

    @PostMapping("/users")
    fun createUser(@RequestBody user: UserSignupRequestDto): User {
        return userService.addUser(user)
    }
}