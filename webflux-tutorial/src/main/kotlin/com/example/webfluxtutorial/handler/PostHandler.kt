package com.example.webfluxtutorial.handler

import com.example.webfluxtutorial.entity.Post
import com.example.webfluxtutorial.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class PostHandler(private val postService: PostService) {

    fun getPosts(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(postService.getPosts(), Post::class.java)
    }

    fun createPost(request: ServerRequest): Mono<ServerResponse> {
        val post = request.bodyToMono(Post::class.java)
        return post
            .flatMap {
                ServerResponse
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(postService.createPost(it), Post::class.java)
            }
    }
}