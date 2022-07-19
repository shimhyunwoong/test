/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.orders.controller

import com.example.test.orders.service.OrderService
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Hyunwoong Shim
 */
@RestController
@RequestMapping("/api")
class OrderController(
    private val orderService: OrderService
) {

    @ApiOperation("상품주문")
    @PostMapping("/order/{productId}")
    fun addOrder(
        @AuthenticationPrincipal userDetails: UserDetails,
        @PathVariable(value = "productId") productId: Long,
        @RequestHeader(value = "authorization") token: String
    ): ResponseEntity<Any> {
        return orderService.addOrder(userDetails, productId)
    }

    @ApiOperation("단일 회원 전체 주문 상품 조회")
    @GetMapping("/order/{userInfo}")
    fun getOrder(
        @AuthenticationPrincipal userDetails: UserDetails,
        @PathVariable(value = "userInfo") userInfo: String,
        @RequestHeader(value = "authorization") token: String
    ): ResponseEntity<Any> {
        return orderService.getOrder(userDetails, userInfo)
    }
}