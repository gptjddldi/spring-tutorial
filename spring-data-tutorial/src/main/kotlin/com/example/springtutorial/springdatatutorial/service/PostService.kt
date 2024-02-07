package com.example.springtutorial.springdatatutorial.service

import com.example.springtutorial.springdatatutorial.dto.PostDto
import com.example.springtutorial.springdatatutorial.model.Post
import com.example.springtutorial.springdatatutorial.model.User
import com.example.springtutorial.springdatatutorial.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository){
    fun getPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun createPost(dto: PostDto, author: User): Post {
        val post = Post(
            title = dto.title,
            content = dto.content,
            author = author
        )
        return postRepository.save(post)
    }

    fun getPost(id: Long): Post {
        return postRepository.findById(id).get()
    }

    fun getPostComments(id: Long): List<String?> {
        val post = postRepository.findById(id).get()
        return post.comments.map { it.content }
    }
}