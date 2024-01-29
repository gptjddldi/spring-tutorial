package com.example.springtutorial.springdatatutorial.Post

import com.example.springtutorial.springdatatutorial.user.UserService
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository, private val userService: UserService){
    fun getPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun createPost(post: PostDto): Post {
        val author = userService.getUserById(post.authorId)
        val p = Post(
            id = post.id,
            title = post.title,
            content = post.content,
            author = author
        )

        return postRepository.save(p)
    }
//
//    fun getPostsByAuthor(authorId: Int): List<Post> {
//        return postRepository.findAllByAuthorId(authorId)
//    }
}