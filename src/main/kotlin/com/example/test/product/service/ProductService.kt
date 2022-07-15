/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.product.service

import com.example.test.product.model.Product
import com.example.test.product.repository.ProductRepository
import javax.annotation.PostConstruct
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Hyunwoong Shim
 */
@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    @PostConstruct
    fun addProduct() {
        val product: Product = Product(
            productName = "상품1"
        )
        val product2: Product = Product(
            productName = "상품2"
        )
        val product3: Product = Product(
            productName = "상품3"
        )
        productRepository.save(product)
        productRepository.save(product2)
        productRepository.save(product3)
    }

    @Transactional
    fun createProduct(userDetails: UserDetails, productName: String): Product {
        val product = Product(
            productName = productName
        )
        productRepository.save(product)
        return product
    }
}