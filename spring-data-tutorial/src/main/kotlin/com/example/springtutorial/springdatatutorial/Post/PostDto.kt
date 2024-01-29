package com.example.springtutorial.springdatatutorial.Post

data class PostDto(
    val id: Int,
    val title: String,
    val content: String,
    val authorId: Int
)