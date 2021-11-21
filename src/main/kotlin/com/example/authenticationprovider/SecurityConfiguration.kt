package com.example.authenticationprovider

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@EnableWebSecurity
class SecurityConfiguration(
    private val passwordEncoder: PasswordEncoder,
    private val customUserDetailsService: CustomUserDetailsService,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .csrf()
            .disable()
            .addFilterBefore(getCustomAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests().anyRequest().authenticated()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(CustomAuthenticationProvider(passwordEncoder = passwordEncoder,
            customUserDetailsService = customUserDetailsService))
    }

    fun getCustomAuthenticationFilter(): CustomAuthenticationFilter {
        val customAuthenticationFilter =
            CustomAuthenticationFilter(AntPathRequestMatcher("/custom-login-url", HttpMethod.POST.name))
        customAuthenticationFilter.setAuthenticationManager(authenticationManagerBean())
        customAuthenticationFilter.setAuthenticationSuccessHandler(CustomAuthenticationSuccessHandler())
        customAuthenticationFilter.setAuthenticationFailureHandler(CustomAuthenticationFailureHandler())
        return customAuthenticationFilter
    }
}
