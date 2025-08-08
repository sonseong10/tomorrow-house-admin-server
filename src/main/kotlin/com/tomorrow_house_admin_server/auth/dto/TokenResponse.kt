package com.tomorrow_house_admin_server.auth.dto

data class TokenResponse(
        val accessToken: String,
        val refreshToken: String,
)