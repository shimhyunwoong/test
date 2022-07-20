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
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
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
    @ApiOperation("전체 유저 검색 / 조회")
    @PostMapping("/user/list")
    fun getPage(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody getPage: PageRequestDto,
        @RequestHeader(value = "authorization") token: String
    ): ArrayList<MembersInfoResponseDto> {
        return userService.getMemberList(userDetails, getPage)
    }
}