/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.util

import com.example.test.product.model.Product
import com.example.test.product.repository.ProductRepository
import com.example.test.user.model.User
import com.example.test.user.repository.UserRepository
import javax.annotation.PostConstruct
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * @author Hyunwoong Shim
 */
@Component
class DummyDate(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val passwordEncoder: PasswordEncoder
) {
    //회원 가입
    @PostConstruct
    fun userData() {
        val list = ArrayList<User>()
        for (i: Int in 1..10) {
            var name: String = "name" + i
            val user = User(
                name = name,
                email = "test" + i + "@email.com",
                pw = passwordEncoder.encode("1qassss2w!Ws"),
                phone = 1213343,
                nickName = "nick",
                gender = "M",
                orders = null,
                product = null
            )
            list.add(user)
        }
        userRepository.saveAll(list)
    }

    @PostConstruct
    fun addProduct() {
        val list = ArrayList<Product>()
        for (i: Int in 1..10) {
            val name: String = "\uD83D\uDE00상품" + i
            val product = Product(
                productName = name
            )
            list.add(product)
        }
        productRepository.saveAll(list)
    }
}