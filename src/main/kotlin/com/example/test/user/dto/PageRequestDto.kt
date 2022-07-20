package com.example.test.user.dto

data class PageRequestDto(
    val page: Int, //페이지 번호
    val size: Int, //표시할 정보의 수
    val sortBy: String, //정렬 기준, 예시 필요 혹은 enum
    val isAsc: Boolean, //오름차순, 내림차순
    val userInfo: String?// 이메일, 이름 두개 나눠서 받기
)