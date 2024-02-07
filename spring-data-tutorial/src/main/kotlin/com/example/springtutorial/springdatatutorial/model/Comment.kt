package com.example.springtutorial.springdatatutorial.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull

@Entity
data class Comment(
    @Column(nullable = false)
    var content: String,

    @ManyToOne
    @NotNull
    var post: Post,

    @ManyToOne
    @NotNull
    var author: User
): StandardAuditModel()