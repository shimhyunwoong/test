/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.orders.repository

import com.example.test.orders.model.Orders
import com.example.test.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Hyunwoong Shim
 */
interface OrderRepository : JpaRepository<Orders, Long> {
    fun findByUser(user: User): List<Orders>?
}