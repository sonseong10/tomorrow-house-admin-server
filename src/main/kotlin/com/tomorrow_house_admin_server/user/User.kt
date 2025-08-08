package com.tomorrow_house_admin_server.user


import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, unique = true)
        val username: String,

        @Column(nullable = false)
        val password: String
)