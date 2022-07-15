package com.example.test.controller

import com.example.test.dto.LoginRequestDto
import com.example.test.dto.RegisterRequestDto
import com.example.test.exception.ErrorResponse
import com.example.test.exception.UserException
import com.example.test.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
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
    ): String {
        return userService.login(loginRequestDto)
    }
}