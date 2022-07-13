package com.example.test.controller

import com.example.test.dto.RegisterDto
import com.example.test.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserController(private val userService: UserService) {
    @PostMapping("/register")
    fun register(@RequestBody @Valid registerDto: RegisterDto): String {
        return userService.register(registerDto)
    }
}