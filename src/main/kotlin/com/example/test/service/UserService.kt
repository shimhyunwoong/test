package com.example.test.service

import com.example.test.dto.RegisterDto
import com.example.test.model.User
import com.example.test.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    fun register(registerDto: RegisterDto): String {
        val findUser: User? = userRepository.findByEmail(registerDto.email)

        if(findUser != null) {
            throw NullPointerException("이미 가입된 이메일입니다.")
        }

        if (registerDto.pw != registerDto.pwCheck) {
            throw NullPointerException("비밀번호가 일치하지 않습니다.")
        }

        var gender: String? = null
        if (registerDto.gender == "M") {
            gender = "M"
        } else if (registerDto.gender == "W") {
            gender = "W"
        }

        val user = User(registerDto.name!!, registerDto.email!!, passwordEncoder.encode(registerDto.pw!!), registerDto.phone!!, registerDto.nickName!!, gender)
        userRepository.save(user)
        return "회원가입 완료"
    }
}