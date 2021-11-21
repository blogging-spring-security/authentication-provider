package com.example.authenticationprovider

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class CustomAuthenticationFilter(requestMatcher: RequestMatcher) :
    AbstractAuthenticationProcessingFilter(requestMatcher) {
    companion object {
        val objectMapper = jacksonObjectMapper()
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val body: String = retrieveJson(request)
        val userInfo: UserInformation = objectMapper.readValue(body)

        val (username, password) = userInfo

        return authenticationManager.authenticate(CustomToken(username, password))
    }

    private fun retrieveJson(request: HttpServletRequest?): String {
        return request?.reader!!.lines().collect(Collectors.joining(System.lineSeparator()))
    }

    data class UserInformation(val username: String, val password: String)
}
