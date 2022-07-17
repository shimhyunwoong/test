/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.user.model

import com.example.test.orders.model.Orders
import com.example.test.product.model.Product
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userid: Long = 0,

    @Column(nullable = false, length = 20)
    val name: String,

    @Column(nullable = false, length = 100)
    val email: String,

    @Column(nullable = false, length = 100)
    val pw: String,

    @Column(nullable = false, length = 20)
    val phone: Long,

    @Column(nullable = false, length = 30)
    val nickName: String,

    @Column
    val gender: String?,

    @OneToMany(mappedBy = "user")
    val orders: List<Orders>?,

    //ToDo 연관관계 다시 맺기
    //list.add 한 후 size 의 마지막을 가져오기
    @OneToOne
    var product: Product?
) :
    UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String {
        return pw
    }

    //사용자 아이디를 return
    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}