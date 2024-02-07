package com.example.springtutorial.springdatatutorial.service

import com.example.springtutorial.springdatatutorial.dto.CommentDto
import com.example.springtutorial.springdatatutorial.model.Comment
import com.example.springtutorial.springdatatutorial.model.Post
import com.example.springtutorial.springdatatutorial.model.User
import com.example.springtutorial.springdatatutorial.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun create(dto: CommentDto, post: Post, author: User): String {
        val comment = Comment(
            content = dto.content,
            author = author,
            post = post
        )
        return commentRepository.save(comment).content
    }
}