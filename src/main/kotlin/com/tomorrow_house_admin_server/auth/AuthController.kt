package com.tomorrow_house_admin_server.auth

import com.tomorrow_house_admin_server.auth.dto.LoginRequest
import com.tomorrow_house_admin_server.auth.dto.TokenResponse
import com.tomorrow_house_admin_server.user.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
        private val userService: UserService,
        private val passwordEncoder: PasswordEncoder,
        private val jwtUtil: JwtUtil,
        private val refreshTokenService: RefreshTokenService,
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<TokenResponse> {
        val user = userService.findByUsername(request.username)
                ?: return ResponseEntity.badRequest().build()

        if (!passwordEncoder.matches(request.password, user.password)) {
            return ResponseEntity.badRequest().build()
        }

        val accessToken = jwtUtil.generateAccessToken(user.username)
        val refreshToken = jwtUtil.generateRefreshToken(user.username)

        refreshTokenService.saveRefreshToken(user.username, refreshToken, jwtUtil.jwtConfig.refreshTokenExpirationMs)

        return ResponseEntity.ok(TokenResponse(accessToken, refreshToken))
    }

    @PostMapping("/refresh")
    fun refresh(@RequestHeader("Authorization") refreshTokenHeader: String): ResponseEntity<TokenResponse> {
        if (!refreshTokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build()
        }
        val refreshToken = refreshTokenHeader.substring(7)

        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(401).build()
        }

        val username = jwtUtil.getUsernameFromToken(refreshToken)
        val savedRefreshToken = refreshTokenService.getRefreshToken(username)

        if (savedRefreshToken == null || savedRefreshToken != refreshToken) {
            return ResponseEntity.status(401).build()
        }

        val newAccessToken = jwtUtil.generateAccessToken(username)
        val newRefreshToken = jwtUtil.generateRefreshToken(username)
        refreshTokenService.saveRefreshToken(username, newRefreshToken, jwtUtil.jwtConfig.refreshTokenExpirationMs)

        return ResponseEntity.ok(TokenResponse(newAccessToken, newRefreshToken))
    }
}
