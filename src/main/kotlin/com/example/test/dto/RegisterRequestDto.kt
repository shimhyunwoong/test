package com.example.test.dto

data class RegisterRequestDto(
    val name: String,
    val email: String,
    val pw: String,
    val pwCheck: String,
    val phone: Int,
    val nickName: String,
    val gender: String?
)