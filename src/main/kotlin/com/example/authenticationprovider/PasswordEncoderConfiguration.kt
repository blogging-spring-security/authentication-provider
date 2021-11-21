package com.example.authenticationprovider

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

@Configuration
class PasswordEncoderConfiguration {
    companion object {
        val encoders = mapOf(
            "bcrypt" to BCryptPasswordEncoder(),
            "scrypt" to SCryptPasswordEncoder(),
            "noOp" to NoOpPasswordEncoder.getInstance(),
        )
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
}
