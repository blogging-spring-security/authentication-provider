package com.example.authenticationprovider

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(val passwordEncoder: PasswordEncoder) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        // mock as if CustomUserDetails object is retrieved using username from database and etc
        val mockUserDetails = CustomUserDetails(username = "user",
            password = passwordEncoder.encode("1234"),
            authorities = mutableListOf(SimpleGrantedAuthority("USER")))

        return mockUserDetails
    }
}
