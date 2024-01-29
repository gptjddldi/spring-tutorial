package com.example.springtutorial.springdatatutorial.user

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository){
    fun getUsers(): Iterable<User> {
        return userRepository.findAll()
    }

    fun getUserById(id: Int): User {
        return userRepository.findById(id).get()
    }

    fun addUser(user: User): User {
        return userRepository.save(user)
    }
}