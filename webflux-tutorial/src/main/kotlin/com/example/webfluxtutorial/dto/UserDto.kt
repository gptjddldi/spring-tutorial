package com.example.webfluxtutorial.dto

import com.example.webfluxtutorial.entity.User

data class UserDto(
    val id: Long?,
    val name: String,
    val password: String
) {
    companion object Mapper {
        fun from(user: User): UserDto {
            return UserDto(
                id = user.id,
                name = user.name,
                password = user.password
            )
        }
    }
}