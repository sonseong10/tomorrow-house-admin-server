package com.tomorrow_house_admin_server.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
        info = Info(
                title = "Tomorrow House Admin API",
                version = "v1",
                description = "JWT + Redis 인증 API 문서"
        )
)
class SwaggerConfig
