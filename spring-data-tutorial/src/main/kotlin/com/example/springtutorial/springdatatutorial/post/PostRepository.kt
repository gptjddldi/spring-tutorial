package com.example.springtutorial.springdatatutorial.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Int> {
//    fun findAllByAuthorId(authorId: Int): List<Post> {
//        return findAll().filter { it.author.id == authorId }
//    }

}