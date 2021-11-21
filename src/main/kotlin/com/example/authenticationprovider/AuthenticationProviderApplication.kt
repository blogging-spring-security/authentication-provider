package com.example.authenticationprovider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class AuthenticationProviderApplication

fun main(args: Array<String>) {
	runApplication<AuthenticationProviderApplication>(*args)
}

@RestController
@RequestMapping("/")
class ExampleController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }
}
