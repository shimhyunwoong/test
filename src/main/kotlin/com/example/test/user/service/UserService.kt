/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.service

import com.example.test.orders.repository.OrderRepository
import com.example.test.product.dto.ProductResponseDto
import com.example.test.product.repository.ProductRepository
import com.example.test.user.dto.MembersInfoResponseDto
import com.example.test.user.dto.UserInfoResponseDto
import com.example.test.user.model.User
import com.example.test.user.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Hyunwoong Shim
 */
@Service
class UserService(
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
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

    //ToDo Join 사용해서 쿼리 줄이기
    @Transactional
    fun getMemberList(userDetails: UserDetails): ResponseEntity<Any> {
        val users: List<User> = userRepository.findAll()

        val response: ArrayList<MembersInfoResponseDto> = ArrayList()

        for (find in users) {
            val userinfo = UserInfoResponseDto(
                name = find.name,
                email = find.email,
                nickname = find.email,
                phone = find.phone,
                gender = find.gender
            )

            if (find.orders?.size!! == 0) {
                val lastOrder = ProductResponseDto(
                    orderNum = null,
                    productName = null,
                    orderDate = null
                )

                val memberInfo = MembersInfoResponseDto(
                    userInfo = userinfo,
                    lastOrder = lastOrder
                )
                response.add(memberInfo)

            } else {
                val lastOrder = ProductResponseDto(
                    orderNum = find.orders?.get(find.orders!!.size -1)!!.orderNum,
                    productName = find.product?.productName,
                    orderDate = find.orders?.get(find.orders!!.size -1)!!.createdAt
                )

                val memberInfo = MembersInfoResponseDto(
                    userInfo = userinfo,
                    lastOrder = lastOrder
                )
                response.add(memberInfo)
            }

        }

        return ResponseEntity
            .ok()
            .body(response)
    }
}
