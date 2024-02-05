package com.example.springtutorial.springdatatutorial.Post

import com.example.springtutorial.springdatatutorial.user.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository, private val userService: UserService){
    fun getPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun createPost(post: PostDto): Post {
        val email = SecurityContextHolder.getContext().authentication.name
        val author = userService.getUserByEmail(email)
        val p = Post(
            id = post.id,
            title = post.title,
            content = post.content,
            author = author
        )

        return postRepository.save(p)
    }
}