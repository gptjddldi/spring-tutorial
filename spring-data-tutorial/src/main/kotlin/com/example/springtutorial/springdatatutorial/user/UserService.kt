package com.example.springtutorial.springdatatutorial.user

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository){
    fun getUsers(): Iterable<User> {
        return userRepository.findAll()
    }

    fun addUser(user: User): User {
        return userRepository.save(user)
    }
}