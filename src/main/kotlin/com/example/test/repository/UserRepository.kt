package com.example.test.repository

import com.example.test.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long?> {
    fun findByEmail(email: String?): User?
}