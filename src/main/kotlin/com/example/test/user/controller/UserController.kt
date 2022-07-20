/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.controller

import com.example.test.user.dto.MembersInfoResponseDto
import com.example.test.user.dto.PageRequestDto
import com.example.test.user.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

/**
 * @author Hyunwoong Shim
 */
@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {
    @ApiOperation("전체 유저 검색 / 조회")
    @PostMapping("/user/list") //get, @ModelAttribute
    fun getPage(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody getPage: PageRequestDto,
        @RequestHeader(value = "authorization") token: String
    ): ArrayList<MembersInfoResponseDto> { //전체 페이지가 얼마인지 알 수가 없음, 페이지 객체 사용하는게 나음, 토탈엘리먼트,
        return userService.getMemberList(userDetails, getPage)
        //세션으로 로그인 구현해도 됨
    }
}