package com.tomorrow_house_admin_server.auth

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RefreshTokenService(
        private val redisTemplate: RedisTemplate<String, Any>
) {
    fun saveRefreshToken(username: String, refreshToken: String, expirationMs: Long) {
        redisTemplate.opsForValue().set("refresh:$username", refreshToken, expirationMs, TimeUnit.MILLISECONDS)
    }

    fun getRefreshToken(username: String): String? {
        return redisTemplate.opsForValue().get("refresh:$username") as? String
    }

    fun deleteRefreshToken(username: String) {
        redisTemplate.delete("refresh:$username")
    }
}
