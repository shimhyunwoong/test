/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.dto

data class RegisterRequestDto(
//    @field:NotBlank
//    @field:Pattern(regexp = "/^[가-힣a-zA-Z]+$/", message = "한글, 영문 대소문자만 가능합니다.")
    val name: String,

//    @field:NotBlank
//    @field:Email(message = "올바른 형식의 이메일이 아닙니다.")
    val email: String,

//    @field:NotBlank
//    @field:Pattern(
//        regexp = "^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$ %^&*-]).{10,}$",
//        message = "영문 대문자, 소문자, 특수 문자, 숫자 각 1개 이상씩 포함해야 합니다."
//    )
    val pw: String,

//    @field:NotBlank
    val pwCheck: String,

//    @field:NotBlank
//    @field:Pattern(regexp = "[^0-9]", message = "숫자만 입력 할 수 있습니다.")
    val phone: Int,

//    @field:NotBlank
//    @field:Pattern(regexp = "/^[a-z]*$/", message = "영문 소문자만 입력 가능합니다.")
    val nickName: String,

    val gender: String?
)