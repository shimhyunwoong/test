/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.service

import com.example.test.user.dto.UserInfoResponseDto
import com.example.test.user.model.User
import com.example.test.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

/**
 * @author Hyunwoong Shim
 */
@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getUserInfo(userDetails: UserDetails): UserInfoResponseDto {
        val user: User? = userRepository.findByEmail(userDetails.username)
        val userInfoResponseDto = UserInfoResponseDto(
            name = user!!.name,
            email = user.email,
            nickname = user.nickName,
            phone = user.phone,
            gender = user.gender
        )
        return userInfoResponseDto
    }
}