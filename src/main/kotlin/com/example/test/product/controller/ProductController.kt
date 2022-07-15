/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.product.controller

import com.example.test.product.model.Product
import com.example.test.product.service.ProductService
import io.swagger.annotations.ApiOperation
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Hyunwoong Shim
 */

@RestController
@RequestMapping("/api")
class ProductController(
    private val productService: ProductService,
) {
    @ApiOperation("상품 추가")
    @PostMapping("/product/{name}")
    fun addProduct(
        @AuthenticationPrincipal userDetails: UserDetails,
        @PathVariable(value = "name") productName: String
    ): Product {
        return productService.createProduct(userDetails, productName)
    }
}