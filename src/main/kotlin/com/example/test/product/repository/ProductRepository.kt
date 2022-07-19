/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.product.repository

import com.example.test.product.model.Product
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Hyunwoong Shim
 */
interface ProductRepository : JpaRepository<Product, Long> {
    fun findByProductId(product: Long?): Product?
    fun findByProductName(productName: String): Product?
}