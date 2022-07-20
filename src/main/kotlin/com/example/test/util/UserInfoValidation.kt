/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.util

import com.example.test.user.model.User
import com.example.test.user.repository.UserRepository
import org.springframework.stereotype.Component

/**
 * @author Hyunwoong Shim
 */
@Component
class UserInfoValidation(
    private val userRepository: UserRepository
) {
    // 이메일, 이름 체크
    fun userInfoStringCheck(user: String): User? {
        val findUser: User? = when (user.contains("@")) {
            true -> userRepository.findByEmail(user)
            else -> userRepository.findByName(user)
        }
        return findUser
    }

    //검색 기능
    fun searchStringCheck(user: String): List<User>? {
        val findUser: List<User>? = when (user.contains("@")) {
            true -> userRepository.findByEmailContaining(user)
            else -> userRepository.findByNameContaining(user)
        }
        return findUser
    }
}