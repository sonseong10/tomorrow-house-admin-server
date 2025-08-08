package com.tomorrow_house_admin_server.auth.dto

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
        @field:NotBlank val username: String,
        @field:NotBlank val password: String,
)
