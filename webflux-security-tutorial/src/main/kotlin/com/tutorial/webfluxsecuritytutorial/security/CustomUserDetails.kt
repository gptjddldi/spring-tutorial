package com.tutorial.webfluxsecuritytutorial.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import com.tutorial.webfluxsecuritytutorial.user.User as MyUser

data class CustomUserDetails(private val user: MyUser) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf("User").map { SimpleGrantedAuthority(it) }.toList()
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}