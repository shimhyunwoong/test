package com.example.test.service

import com.example.test.dto.RegisterDto
import com.example.test.model.User
import com.example.test.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun register(registerDto: RegisterDto): String {
        var gender: String? = null
        if (registerDto.gender == "M") {
            gender = "M"
        } else if (registerDto.gender == "W") {
            gender = "W"
        }

        userRepository.save(User(
                name = registerDto.name,
        ))









        return "회원가입 완료"
    }
}