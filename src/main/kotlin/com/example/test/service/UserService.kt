package com.example.test.service

import com.example.test.dto.RegisterRequestDto
import com.example.test.exception.ErrorCode
import com.example.test.exception.UserException
import com.example.test.model.User
import com.example.test.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
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
}