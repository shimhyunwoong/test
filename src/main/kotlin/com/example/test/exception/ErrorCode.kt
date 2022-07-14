/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.exception

import org.springframework.http.HttpStatus

/**
 * @author Hyunwoong Shim
 */
enum class ErrorCode(
    val httpStatus: HttpStatus,
    val errorCode: String,
    val message: String
) {
    ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "USER-1001", "이미 가입된 아이디 입니다.")
}