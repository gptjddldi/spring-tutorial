package com.example.webfluxtutorial.repository

import com.example.webfluxtutorial.entity.User
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: R2dbcRepository<User, Long> {
}