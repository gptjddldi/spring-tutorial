package com.example.springtutorial.springdatatutorial.repository

import com.example.springtutorial.springdatatutorial.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long> {
//    fun findAllByAuthorId(authorId: Int): List<Post> {
//        return findAll().filter { it.author.id == authorId }
//    }

}