package com.example.webfluxtutorial.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("posts")
data class Post(
    @Id
    @Column("id") val id: Long?,
    @Column("contents") val contents: String,
    @Column("user_id") val userId: Long,
)