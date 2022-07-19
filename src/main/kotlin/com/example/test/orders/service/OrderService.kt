/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.orders.service

import com.example.test.orders.dto.OrderResponseDto
import com.example.test.orders.model.Orders
import com.example.test.orders.repository.OrderRepository
import com.example.test.product.dto.ProductResponseDto
import com.example.test.product.model.Product
import com.example.test.product.repository.ProductRepository
import com.example.test.user.model.User
import com.example.test.user.repository.UserRepository
import com.example.test.util.UserInfoValidation
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Hyunwoong Shim
 */
@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
    private val validation: UserInfoValidation
) {
    @Transactional
    fun addOrder(
        userDetails: UserDetails,
        productId: Long
    ): ResponseEntity<Any> {
        val user: User = userRepository.findByEmail(userDetails.username)
            ?: return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("유저 정보를 찾을 수 없습니다.")

        val product: Product = productRepository.findByIdOrNull(productId)
            ?: return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("없는 상품입니다.")

        val orderNum = getRandomNum(12)
        val orders = Orders(
            orderNum = orderNum,
            product = product,
            user = user
        )

        orderRepository.save(orders)

        user.product = product
        userRepository.save(user)

        return ResponseEntity
            .ok()
            .body("주문 완료")
    }

    @Transactional
    fun getOrder(userDetails: UserDetails, userInfo: String): ResponseEntity<Any> {
        val user: User = validation.userInfoStringCheck(userInfo)
            ?: return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("해당 유저를 찾을 수 없습니다.")

        val orders: List<Orders> = orderRepository.findByUser(user)
            ?: return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("주문 상품이 존재하지 않습니다.")

        val response = ArrayList<ProductResponseDto>()

        for (order in orders) {
            val product: Product = productRepository.findByProductId(order.product.productId)
                ?: return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("상품이 존재하지 않습니다.")
            val productResponseDto = ProductResponseDto(
                orderNum = order.orderNum,
                orderDate = order.createdAt,
                productName = product.productName
            )
            response.add(productResponseDto)
        }

        val ordersResponseDto = OrderResponseDto(
            username = user.name,
            response = response
        )

        return ResponseEntity
            .ok()
            .body(ordersResponseDto)
    }

    //OrderNum 랜덤한 영문 대문자, 숫자 조합
    private fun getRandomNum(length: Int): String {
        val charset = ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}