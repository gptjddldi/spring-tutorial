package com.example.springtutorial.springdatatutorial.repository

import com.example.springtutorial.springdatatutorial.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}