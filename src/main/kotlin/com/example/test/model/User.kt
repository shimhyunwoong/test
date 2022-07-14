package com.example.test.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class User(name: String, email: String, pw: String, phone: Int, gender: String?, nickName: String) : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userid: Long? = null

    @Column(nullable = false, length = 20)
    var name: String = name

    @Column(nullable = false, length = 100)
    var email: String = email

    @Column(nullable = false, length = 100)
    var pw: String = pw

    @Column(nullable = false, length = 20)
    var phone: Int = phone

    @Column(nullable = false, length = 20)
    var nickName: String = nickName

    @Column
    var gender: String? = gender

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