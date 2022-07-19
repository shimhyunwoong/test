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
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    @Transactional
    fun createProduct(userDetails: UserDetails, productName: String): ResponseEntity<Any> {
        val findProduct: Product = productRepository.findByProductName(productName)
            ?: return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("이미 등록된 상품이 존재합니다.")

        val product = Product(
            productName = productName
        )
        productRepository.save(product)
        return ResponseEntity
            .ok()
            .body(product)
    }
}