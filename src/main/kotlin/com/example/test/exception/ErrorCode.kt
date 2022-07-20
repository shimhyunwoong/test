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
    NOT_FOUN_USER(HttpStatus.NOT_FOUND, "USER-1001", "유저 정보를 찾을 수 없습니다."),
    ALREADY_USER(HttpStatus.BAD_REQUEST, "USER-1002", "이미 가입된 아이디 입니다."),
    FAIL_PASSWORD(HttpStatus.BAD_REQUEST, "USER-1003", "비밀번호가 일치하지 않습니다."),

    NOT_FOND_ORDER(HttpStatus.NOT_FOUND, "ORDER-2001", "주문 상품이 존재하지 않습니다."),

    NOT_FOND_PRODUCT(HttpStatus.NOT_FOUND, "PRODUCT-3001", "상품이 존재하지 않습니다."),
    ALREADY_PRODUCT(HttpStatus.BAD_REQUEST, "PRODUCT-3002", "이미 등록된 상품이 존재합니다.")
}