package com.example.webfluxtutorial.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    @Column("id") val id: Long?,
    @Column("name") val name: String,
    @Column("password") val password: String
)
