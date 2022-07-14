package com.example.test.controller

import com.example.test.dto.LoginRequestDto
import com.example.test.dto.RegisterRequestDto
import com.example.test.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun register(
        @RequestBody registerDto: RegisterRequestDto
    ): ResponseEntity<Any> {

        return userService.register(registerDto)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody loginRequestDto: LoginRequestDto
    ): HttpHeaders {
        return userService.login(loginRequestDto)
    }
}