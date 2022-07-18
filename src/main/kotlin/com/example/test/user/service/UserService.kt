/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.service

import com.example.test.product.dto.ProductResponseDto
import com.example.test.user.dto.MembersInfoResponseDto
import com.example.test.user.dto.PageRequestDto
import com.example.test.user.dto.UserInfoResponseDto
import com.example.test.user.dto.model.User
import com.example.test.user.repository.UserRepository
import com.example.test.util.Validation
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Hyunwoong Shim
 */
@Service
class UserService(
    private val userRepository: UserRepository,
    private val validation: Validation
) {
    fun getUserInfo(userDetails: UserDetails, userInfo: String): ResponseEntity<Any> {

        val findUser: User? = validation.variableCheck(userInfo)

        if (findUser == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("해당 유저를 찾을 수 없습니다.")
        }

        val userInfoResponseDto = UserInfoResponseDto(
            name = findUser.name,
            email = findUser.email,
            nickname = findUser.nickName,
            phone = findUser.phone,
            gender = findUser.gender
        )
        return ResponseEntity
            .ok()
            .body(userInfoResponseDto)
    }

    @Transactional
    fun getMemberList(userDetails: UserDetails): ResponseEntity<Any> {
        val users: List<User> = userRepository.findAll()

        val response: ArrayList<MembersInfoResponseDto> = ArrayList()

        //TODO 리팩토링 하기
        for (find in users) {
            val userinfo = UserInfoResponseDto(
                name = find.name,
                email = find.email,
                nickname = find.email,
                phone = find.phone,
                gender = find.gender
            )

            if (find.orders?.size!! == 0) {
                val lastOrder = ProductResponseDto(
                    orderNum = null,
                    productName = null,
                    orderDate = null
                )

                val memberInfo = MembersInfoResponseDto(
                    userInfo = userinfo,
                    lastOrder = lastOrder
                )
                response.add(memberInfo)

            } else {
                val lastOrder = ProductResponseDto(
                    orderNum = find.orders?.get(find.orders!!.size - 1)!!.orderNum,
                    productName = find.product?.productName,
                    orderDate = find.orders?.get(find.orders!!.size - 1)!!.createdAt
                )

                val memberInfo = MembersInfoResponseDto(
                    userInfo = userinfo,
                    lastOrder = lastOrder
                )
                response.add(memberInfo)
            }

        }
        return ResponseEntity
            .ok()
            .body(response)
    }

    //페이징
    @Transactional
    fun getPage(userDetails: UserDetails, page: PageRequestDto): ArrayList<MembersInfoResponseDto> {
        val direction: Sort.Direction =
            when {
                page.isAsc -> Sort.Direction.ASC
                else -> Sort.Direction.DESC
            }
        val sort: Sort = Sort.by(direction, page.sortBy)
        val pageable: Pageable = PageRequest.of(page.page - 1, page.size, sort)

        var pageUser: Page<User> = userRepository.findAll(pageable)

        println(pageUser.size)
        println(page.size)

        if (page.size > pageUser.size) {
            val pageable: Pageable = PageRequest.of(page.page - 1, pageUser.size, sort)
            pageUser = userRepository.findAll(pageable)
        }

        val result: ArrayList<MembersInfoResponseDto> = ArrayList()

        for (find in pageUser) {
            val userInfo = UserInfoResponseDto(
                name = find.name,
                email = find.email,
                nickname = find.nickName,
                phone = find.phone,
                gender = find.gender
            )
            var product: ProductResponseDto

            if (find.orders?.size == 0) {
                product = ProductResponseDto(
                    orderNum = null,
                    productName = null,
                    orderDate = null
                )
            } else {
                product = ProductResponseDto(
                    orderNum = find.product?.productName,
                    productName = find.product?.productName,
                    orderDate = find.orders?.get(find.orders?.size!!)?.createdAt
                )
            }
            val membersInfo = MembersInfoResponseDto(
                userInfo = userInfo,
                lastOrder = product
            )
            result.add(membersInfo)
        }
        return result
    }
}
