package com.example.test.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class User(name: String, email: String, pw: String, phone: Int, gender: String, nickName: String?): UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userid: Long? = null

    @Column(nullable = false, length = 20)
    var name: String = name

    @Column(nullable = false, length = 100)
    var email: String = email

    @Column(nullable = false, length = 20)
    var pw: String = pw

    @Column(nullable = false, length = 20)
    var phone: Int = phone

    @Column(nullable = false, length = 20)
    var nickName: String? = nickName

    @Column
    var gender: String = gender

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