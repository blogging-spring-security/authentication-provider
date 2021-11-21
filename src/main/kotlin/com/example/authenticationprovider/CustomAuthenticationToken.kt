package com.example.authenticationprovider

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class CustomAuthenticationToken : AbstractAuthenticationToken {
    var username: String
    var password: String

    constructor(username: String, password: String) : super(mutableListOf(SimpleGrantedAuthority("USER"))) {
        this.username = username
        this.password = password
    }


    constructor(username: String, password: String, authorities: MutableCollection<out GrantedAuthority>?) : super(
        authorities) {
        super.setAuthenticated(true)
        this.username = username
        this.password = password
    }

    override fun getCredentials(): Any {
        return this.password
    }

    override fun getPrincipal(): Any {
        return this.username
    }
}
