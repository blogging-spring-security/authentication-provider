package com.example.authenticationprovider

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder

class CustomAuthenticationProvider(
    private val customUserDetailsService: CustomUserDetailsService,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val customAuthentication = authentication as CustomAuthenticationToken

        val username = customAuthentication.username
        val password = customAuthentication.password

        val savedUser = customUserDetailsService.loadUserByUsername(username)

        if (savedUser == null) throw BadCredentialsException("No such user with username $username exists")
        else if (!passwordEncoder.matches(password, savedUser.password)) throw BadCredentialsException("Password mismatch")

        return CustomAuthenticationToken(username, password, mutableListOf(SimpleGrantedAuthority("USER")))
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.equals(CustomAuthenticationToken::class.java) ?: false
    }
}
