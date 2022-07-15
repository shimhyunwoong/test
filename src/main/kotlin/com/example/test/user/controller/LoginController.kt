/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.controller

import com.example.test.user.dto.LoginRequestDto
import com.example.test.user.dto.RegisterRequestDto
import com.example.test.user.service.LoginService
import javax.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class LoginController(
    private val loginService: LoginService
) {
    @PostMapping("/register")
    fun register(
        @RequestBody @Valid registerDto: RegisterRequestDto
    ): ResponseEntity<Any> {

        return loginService.register(registerDto)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody loginRequestDto: LoginRequestDto
    ): ResponseEntity<Any> {
        return loginService.login(loginRequestDto)
    }
}