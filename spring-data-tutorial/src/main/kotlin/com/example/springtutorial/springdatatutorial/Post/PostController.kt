package com.example.springtutorial.springdatatutorial.Post

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(private val postService: PostService) {
    @GetMapping("/posts")
    fun getPosts(): List<Post> {
        return postService.getPosts()
    }

    @PostMapping("/posts")
    fun createPost(@RequestBody post: PostDto): String {
        return postService.createPost(post).title
    }
}