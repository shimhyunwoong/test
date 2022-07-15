/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.orders.controller

import com.example.test.orders.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
    @PostMapping("/order/{productId}")
    fun addOrder(
        @AuthenticationPrincipal userDetails: UserDetails,
        @PathVariable(value = "productId") productId: Long
    ): ResponseEntity<Any> {
        return orderService.addOrder(userDetails, productId)
    }

    @GetMapping("/order")
    fun getOrder(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<Any> {
        return orderService.getOrder(userDetails)
    }
}