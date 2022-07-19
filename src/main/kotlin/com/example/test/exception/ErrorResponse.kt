/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.exception

import java.time.LocalDateTime

/**
 * @author Hyunwoong Shim
 */
data class ErrorResponse(
    val localDateTime: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val code: String,
    val message: String,
    val desscription: String
)