package com.example.springtutorial.springdatatutorial.repository

import com.example.springtutorial.springdatatutorial.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {
    fun findUserByEmail(email: String): User
}