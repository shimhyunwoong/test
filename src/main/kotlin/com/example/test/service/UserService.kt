package com.example.test.service

import com.example.test.dto.LoginRequestDto
import com.example.test.dto.RegisterRequestDto
import com.example.test.exception.ErrorCode
import com.example.test.exception.UserException
import com.example.test.model.User
import com.example.test.repository.UserRepository
import com.example.test.security.jwt.JwtTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun register(registerDto: RegisterRequestDto): ResponseEntity<Any> {

        val findUser: User? = userRepository.findByEmail(registerDto.email)

        if (findUser != null) {
            throw UserException(ErrorCode.ALREADY_REGISTERED)
        }

        val user = User(
            name = registerDto.name,
            email = registerDto.email,
            pw = passwordEncoder.encode(registerDto.pw),
            phone = registerDto.phone,
            nickName = registerDto.nickName,
            gender = registerDto.gender
        )

        userRepository.save(user)
        return ResponseEntity
            .ok()
            .body(true)
    }

    @Transactional
    fun login(loginRequestDto: LoginRequestDto): String {
        val user: User? = userRepository.findByEmail(loginRequestDto.email)

        if (user == null) {
            throw UserException(ErrorCode.NOT_FOUND_USER)
        }

        if (!passwordEncoder.matches(loginRequestDto.pw, user.password)) {
            throw UserException(ErrorCode.FAIL_LOGIN)
        }

        //todo 값 헤더로 보내기
        val token: String = jwtTokenProvider.createToken(user.email)

        return token
    }
}