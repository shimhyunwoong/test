/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.service

import com.example.test.exception.CustomException
import com.example.test.exception.ErrorCode
import com.example.test.product.dto.ProductResponseDto
import com.example.test.user.dto.MembersInfoResponseDto
import com.example.test.user.dto.PageRequestDto
import com.example.test.user.dto.UserInfoResponseDto
import com.example.test.user.model.User
import com.example.test.user.repository.UserRepository
import com.example.test.util.UserInfoValidation
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Hyunwoong Shim
 */
@Service
class UserService(
    private val userRepository: UserRepository,
    private val validation: UserInfoValidation
) {
    fun getUserInfo(userDetails: UserDetails, userInfo: String): ArrayList<UserInfoResponseDto> {
        val findUser: List<User>? = validation.searchStringCheck(userInfo)

        if (findUser!!.isEmpty()) {
            throw CustomException(ErrorCode.NOT_FOUN_USER)
        }

        val result: ArrayList<UserInfoResponseDto> = ArrayList()

        for (user in findUser) {
            val userInfoResponseDto = UserInfoResponseDto(
                name = user.name,
                email = user.email,
                nickname = user.nickName,
                phone = user.phone,
                gender = user.gender
            )
            result.add(userInfoResponseDto)
        }
        return result
    }

    @Transactional
    fun getMemberList(userDetails: UserDetails): ArrayList<MembersInfoResponseDto> {
        val users: List<User> = userRepository.findAll()

        val response: ArrayList<MembersInfoResponseDto> = ArrayList()

        for (find in users) {
            val userinfo = UserInfoResponseDto(
                name = find.name,
                email = find.email,
                nickname = find.email,
                phone = find.phone,
                gender = find.gender
            )

            val lastOrder = when (find.orders?.size) {
                0 -> ProductResponseDto(
                    orderNum = null,
                    productName = null,
                    orderDate = null
                )
                else -> ProductResponseDto(
                    orderNum = find.orders?.get(find.orders!!.size - 1)!!.orderNum,
                    productName = find.product?.productName,
                    orderDate = find.orders?.get(find.orders!!.size - 1)!!.createdAt
                )
            }

            val memberInfo = MembersInfoResponseDto(
                userInfo = userinfo,
                lastOrder = lastOrder
            )
            response.add(memberInfo)
        }
        return response
    }

    //페이징
    @Transactional
    fun getPage(userDetails: UserDetails, page: PageRequestDto): ArrayList<MembersInfoResponseDto> {
        val direction: Sort.Direction =
            when (page.isAsc) {
                true -> Sort.Direction.ASC
                else -> Sort.Direction.DESC
            }

        val sort: Sort = Sort.by(direction, page.sortBy)
        val pageable: Pageable = PageRequest.of(page.page - 1, page.size, sort)
        var pageUser: Page<User> = userRepository.findAll(pageable)

        if (page.size >= pageUser.size) {
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

            val product: ProductResponseDto = when (find.orders!!.size) {
                0 -> ProductResponseDto(
                    orderNum = null,
                    productName = null,
                    orderDate = null
                )
                else -> ProductResponseDto(
                    orderNum = find.product?.productName,
                    productName = find.product?.productName,
                    orderDate = find.orders?.get(find.orders?.size!! - 1)?.createdAt
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
