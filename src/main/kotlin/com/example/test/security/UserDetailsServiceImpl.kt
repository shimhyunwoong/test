package com.example.test.security

import com.example.test.model.User
import com.example.test.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByEmail(username) ?: throw UsernameNotFoundException("존재하지 않는 username 입니다.")

        return UserDetailsImpl(user)
    }
}