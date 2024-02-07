package com.example.springtutorial.springdatatutorial.controller

import com.example.springtutorial.springdatatutorial.model.Post
import com.example.springtutorial.springdatatutorial.dto.PostDto
import com.example.springtutorial.springdatatutorial.security.AuthenticationFacade
import com.example.springtutorial.springdatatutorial.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService,
    private val authenticationFacade: AuthenticationFacade
) {
    @GetMapping("/posts")
    fun getPosts(): List<Post> {
        return postService.getPosts()
    }

    @PostMapping("/posts")
    fun createPost(@RequestBody dto: PostDto): Long {
        return postService.createPost(dto, authenticationFacade.authenticatedUserEntity).id
    }
}