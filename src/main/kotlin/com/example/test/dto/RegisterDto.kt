package com.example.test.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

class RegisterDto {

    @NotBlank
    @Pattern(regexp = "/^[가-힣a-zA-Z]+$/", message = "한글, 영문 대소문자만 가능합니다.")
    val name: String? = null

    @NotBlank
    @Email(message = "올바른 형식의 이메일이 아닙니다.")
    val email: String? = null

    @NotBlank
    @Pattern(regexp = "^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$ %^&*-]).{10,}$",
            message = "영문 대문자, 소문자, 특수 문자, 숫자 각 1개 이상씩 포함해야 합니다.")
    val pw: String? = null

    @NotBlank
    val pwCheck: String? = null

    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "숫자만 입력 할 수 있습니다.")
    val phone: Int? = null

    @NotBlank
    @Pattern(regexp = "/^[a-z]*$/", message = "영문 소문자만 입력 가능합니다.")
    val nickName: String? = null

    val gender: String? = null
}