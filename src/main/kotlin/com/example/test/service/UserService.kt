package com.example.test.service

import com.example.test.dto.LoginRequestDto
import com.example.test.dto.RegisterRequestDto
import com.example.test.exception.ErrorCode
import com.example.test.exception.UserException
import com.example.test.model.User
import com.example.test.repository.UserRepository
import com.example.test.security.jwt.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils
) {
    //회원가입
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

    //로그인
    @Transactional
    fun login(loginRequestDto: LoginRequestDto): String {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequestDto.email, loginRequestDto.pw, null)
            )
        } catch (e: BadCredentialsException) {
            throw BadCredentialsException("로그인 실패")
        }

        val token = jwtUtils.createToken(loginRequestDto.email)

        return token
    }
}