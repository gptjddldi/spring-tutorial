package com.example.springtutorial.springdatatutorial.controller

import com.example.springtutorial.springdatatutorial.dto.CommentDto
import com.example.springtutorial.springdatatutorial.model.Post
import com.example.springtutorial.springdatatutorial.dto.PostDto
import com.example.springtutorial.springdatatutorial.security.AuthenticationFacade
import com.example.springtutorial.springdatatutorial.service.CommentService
import com.example.springtutorial.springdatatutorial.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService,
    private val commentService: CommentService,
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

    @GetMapping("/posts/{id}")
    fun getPost(@PathVariable id: Long): Post {
        return postService.getPost(id)
    }

    @GetMapping("/posts/{id}/comments")
    fun getPostComments(@PathVariable id: Long): List<String?> {
        return postService.getPostComments(id)
    }

    @PostMapping("/posts/{id}/comments")
    fun createComment(@PathVariable id: Long, @RequestBody comment: CommentDto): String {
        val post = postService.getPost(id)
        return commentService.create(comment, post, authenticationFacade.authenticatedUserEntity)
    }
}