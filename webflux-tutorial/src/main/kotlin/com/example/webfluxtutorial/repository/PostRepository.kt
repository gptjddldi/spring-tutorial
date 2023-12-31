package com.example.webfluxtutorial.repository

import com.example.webfluxtutorial.entity.Post
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: R2dbcRepository<Post, Long>