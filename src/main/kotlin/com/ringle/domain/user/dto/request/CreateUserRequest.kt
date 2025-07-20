package com.ringle.domain.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateUserRequest(
    @field:NotBlank(message = "이름은 필수입니다")
    @field:Size(min = 1, max = 100, message = "이름은 1-100자 사이여야 합니다")
    val name: String,

    @field:Email(message = "올바른 이메일 형식이어야 합니다")
    @field:NotBlank(message = "이메일은 필수입니다")
    val email: String,
) 
