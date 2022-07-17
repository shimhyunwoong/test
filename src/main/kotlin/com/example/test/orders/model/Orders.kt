/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.orders.model

import com.example.test.product.model.Product
import com.example.test.user.model.User
import com.example.test.util.Timestamped
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

/**
 * @author Hyunwoong Shim
 */
@Entity
class Orders(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0,

    @Column(nullable = false, length = 12)
    val orderNum: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    ) : Timestamped()