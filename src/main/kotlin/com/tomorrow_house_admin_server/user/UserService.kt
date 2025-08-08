package com.tomorrow_house_admin_server.user

import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {
    fun findByUsername(username: String): User? = userRepository.findByUsername(username)
}