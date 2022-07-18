/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.service

import com.example.test.exception.ErrorCode
import com.example.test.exception.UserException
import com.example.test.security.jwt.JwtTokenProvider
import com.example.test.user.dto.LoginRequestDto
import com.example.test.user.dto.RegisterRequestDto
import com.example.test.user.dto.model.User
import com.example.test.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun register(registerDto: RegisterRequestDto): ResponseEntity<Any> {

        val findUser: User? = userRepository.findByEmail(registerDto.email)

        if (findUser != null) {
            return ResponseEntity
                .status(ErrorCode.ALREADY_REGISTERED.httpStatus)
                .body(ErrorCode.ALREADY_REGISTERED.message)
        }
        if (registerDto.pw != registerDto.pwCheck) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("비밀번호가 맞지 않습니다.")
        }

        val user = User(
            name = registerDto.name,
            email = registerDto.email,
            pw = passwordEncoder.encode(registerDto.pw),
            phone = registerDto.phone,
            nickName = registerDto.nickName,
            gender = registerDto.gender,
            orders = null,
            product = null
        )

        userRepository.save(user)
        return ResponseEntity
            .ok()
            .body("Success")
    }

    @Transactional
    fun login(loginRequestDto: LoginRequestDto): ResponseEntity<Any> {
        val user: User? = userRepository.findByEmail(loginRequestDto.email)

        if (user == null) {
            throw UserException(ErrorCode.NOT_FOUND_USER)
        }

        if (!passwordEncoder.matches(loginRequestDto.pw, user.password)) {
            throw UserException(ErrorCode.FAIL_LOGIN)
        }

        val token: String = jwtTokenProvider.createToken(user.email)

        return ResponseEntity
            .ok()
            .body("Beara: " + token)
    }
}