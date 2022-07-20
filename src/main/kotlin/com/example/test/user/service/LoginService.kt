/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.service

import com.example.test.exception.CustomException
import com.example.test.exception.ErrorCode
import com.example.test.security.jwt.JwtTokenProvider
import com.example.test.user.dto.LoginRequestDto
import com.example.test.user.dto.RegisterRequestDto
import com.example.test.user.model.User
import com.example.test.user.repository.UserRepository
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
    fun register(registerDto: RegisterRequestDto): String {

        val findUser: User? = userRepository.findByEmail(registerDto.email)

        if (findUser != null) {
            throw CustomException(ErrorCode.ALREADY_USER)
        }

        if (registerDto.pw != registerDto.pwCheck) {
            throw CustomException(ErrorCode.FAIL_PASSWORD)
        }

        val user = User( //dto 생성자로 만들어서 받아오기
            name = registerDto.name,
            email = registerDto.email,
            pw = passwordEncoder.encode(registerDto.pw),
            phone = registerDto.phone,
            nickName = registerDto.nickName,
            gender = registerDto.gender,
            orders = emptyList(),
            product = null
        )

        userRepository.save(user)
        return "Success"
    }

    @Transactional
    fun login(loginRequestDto: LoginRequestDto): String {
        val user: User = userRepository.findByEmail(loginRequestDto.email)
            ?: throw CustomException(ErrorCode.NOT_FOUN_USER)

        if (!passwordEncoder.matches(loginRequestDto.pw, user.password)) {
            throw CustomException(ErrorCode.FAIL_PASSWORD)
        }
        return jwtTokenProvider.createToken(user.email)
    }
}