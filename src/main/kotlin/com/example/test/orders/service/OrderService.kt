/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.orders.service

import com.example.test.exception.CustomException
import com.example.test.exception.ErrorCode
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
    ): String {
        val user: User = userRepository.findByEmail(userDetails.username)
            ?: throw CustomException(ErrorCode.NOT_FOUN_USER)

        val product: Product = productRepository.findByIdOrNull(productId)
            ?: throw CustomException(ErrorCode.NOT_FOND_PRODUCT)

        val orderNum = getRandomNum(12)
        val orders = Orders(
            orderNum = orderNum,
            product = product,
            user = user
        )

        orderRepository.save(orders)

        user.product = product
        userRepository.save(user)

        return "주문 완료"
    }

    @Transactional
    fun getOrder(uerDetails: UserDetails, userInfo: String): OrderResponseDto {
        val user: User = validation.userInfoStringCheck(userInfo)
            ?: throw CustomException(ErrorCode.NOT_FOUN_USER)

        val orders: List<Orders> = orderRepository.findByUser(user)
            ?: throw CustomException(ErrorCode.NOT_FOND_ORDER)

        val response = ArrayList<ProductResponseDto>()

        for (order in orders) {
            val product: Product = productRepository.findByProductId(order.product.productId)
                ?: throw CustomException(ErrorCode.NOT_FOND_PRODUCT)

            val productResponseDto = ProductResponseDto(
                orderNum = order.orderNum,
                orderDate = order.createdAt,
                productName = product.productName
            )
            response.add(productResponseDto)
        }

        return OrderResponseDto(
            username = user.name,
            response = response
        )
    }

    //OrderNum 랜덤한 영문 대문자, 숫자 조합
    private fun getRandomNum(length: Int): String {
        val charset = ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}