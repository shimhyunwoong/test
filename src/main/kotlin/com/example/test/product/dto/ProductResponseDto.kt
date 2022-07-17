/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.product.dto

import java.time.LocalDateTime
import java.time.ZonedDateTime

/**
 * @author Hyunwoong Shim
 */
data class ProductResponseDto(
    val orderNum: String?,
    val productName: String?,
    val orderDate: LocalDateTime?
)