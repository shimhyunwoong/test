/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.service

import com.example.test.orders.model.Orders
import com.example.test.orders.repository.OrderRepository
import com.example.test.product.dto.ProductResponseDto
import com.example.test.product.model.Product
import com.example.test.product.repository.ProductRepository
import com.example.test.user.dto.MembersInfoResponseDto
import com.example.test.user.dto.UserInfoResponseDto
import com.example.test.user.model.User
import com.example.test.user.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

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
    fun getMemberList(userDetails: UserDetails): ResponseEntity<Any> {
        val users: List<User> = userRepository.findAll()

        val order: List<Orders> = orderRepository.findAll()

        val product: List<Product> = productRepository.findAll()


        val userinfo = UserInfoResponseDto(
            name = user.name,
            email = user.email,
            nickname = user.nickName,
            phone = user.phone,
            gender = user.gender
        )
        val lastOrder = ProductResponseDto(
            orderNum = order.get(order.size).orderNum,
            productName = product.productName,
            orderDate = order.get(order.size).orderDate
        )

        val membersInfo = MembersInfoResponseDto(
            userInfo = userinfo,
            lastOrder = lastOrder
        )

        return ResponseEntity
            .ok()
            .body(membersInfo)
    }
}