package com.example.springtutorial.springdatatutorial.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0L,

    val name: String,
    val email: String,
    val password: String,

    @OneToMany(mappedBy = "author")
    val posts: List<Post> = emptyList(),

    @OneToMany(mappedBy = "author")
    val comments: List<Comment> = emptyList()
): AuditModel(), EntityWithId
