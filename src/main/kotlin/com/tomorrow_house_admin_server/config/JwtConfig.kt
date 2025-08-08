package com.tomorrow_house_admin_server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @Value("\${jwt.access-token-expiration}")
    var accessTokenExpirationMs: Long = 30 * 60 * 1000 // 30분 기본

    @Value("\${jwt.refresh-token-expiration}")
    var refreshTokenExpirationMs: Long = 7 * 24 * 60 * 60 * 1000 // 7일 기본
}
