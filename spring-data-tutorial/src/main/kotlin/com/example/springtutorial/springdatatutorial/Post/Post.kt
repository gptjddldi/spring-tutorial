package com.example.springtutorial.springdatatutorial.Post

import com.example.springtutorial.springdatatutorial.user.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val title: String,
    val content: String,
    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: User
)
