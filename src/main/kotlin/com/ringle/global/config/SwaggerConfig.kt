package com.ringle.global.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .addServersItem(Server().url("/"))
            .info(apiInfo())
    }

    private fun apiInfo(): Info {
        return Info()
            .title("Ringle Backend API")
            .description("[Ringle] AI 튜터 백엔드 구현과제")
            .version("1.0.0")
    }
}
