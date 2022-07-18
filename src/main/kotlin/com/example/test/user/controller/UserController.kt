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
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Hyunwoong Shim
 */
@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {
    @ApiOperation("단일 유저 정보 조회, email or name")
    @GetMapping("/user-info/{userInfo}")
    fun getUserInfo(
        @AuthenticationPrincipal userDetails: UserDetails,
        @PathVariable(value = "userInfo") userInfo: String
    ): ResponseEntity<Any> {
        return userService.getUserInfo(userDetails, userInfo)
    }

    @ApiOperation("전체 유저 조회")
    @GetMapping("/member")
    fun getMemberList(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<Any> {
        return userService.getMemberList(userDetails)
    }

    @ApiOperation("회원 목록 조회")
    @GetMapping("/user/list")
    fun getPage(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody getPage: PageRequestDto
    ): ArrayList<MembersInfoResponseDto> {
        return userService.getPage(userDetails, getPage)
    }
}