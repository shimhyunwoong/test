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
    fun getMemberList(userDetails: UserDetails, page: PageRequestDto): ArrayList<MembersInfoResponseDto> {
        val direction: Sort.Direction =
            when (page.isAsc) {
                true -> Sort.Direction.ASC
                else -> Sort.Direction.DESC
            }

        val result: ArrayList<MembersInfoResponseDto> = ArrayList()

        val sort: Sort = Sort.by(direction, page.sortBy)
        val pageable: Pageable = PageRequest.of(page.page - 1, page.size, sort)

        val pageUser: Page<User> = when (page.userInfo) {
            null -> userRepository.findAll(pageable)
            else -> validation.searchStringCheck(page.userInfo, pageable)
        }

        for (find in pageUser) { //컬렉션맵
            val userInfo = UserInfoResponseDto(
                name = find.name,
                email = find.email,
                nickname = find.nickName,
                phone = find.phone,
                gender = find.gender
            )

            val product: ProductResponseDto = when (find.orders.size) {
                0 -> ProductResponseDto(
                    orderNum = null,
                    productName = null,
                    orderDate = null
                )
                else -> ProductResponseDto(
                    orderNum = find.product?.productName,
                    productName = find.product?.productName,
                    orderDate = find.orders.get(find.orders.size - 1).createdAt
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
