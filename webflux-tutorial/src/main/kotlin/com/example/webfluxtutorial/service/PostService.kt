package com.example.webfluxtutorial.service

import com.example.webfluxtutorial.entity.Post
import com.example.webfluxtutorial.repository.PostRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PostService(private val postRepository: PostRepository) {
    fun getPosts(): Flux<Post> {
        return postRepository.findAll()
    }

    fun createPost(post: Post): Mono<Post> {
        return postRepository.save(post)
    }
}