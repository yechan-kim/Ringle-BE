package com.ringle.domain.user.dto.response

import com.ringle.domain.user.domain.entity.UserEntity
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(userEntity: UserEntity): UserResponse = UserResponse(
            id = userEntity.id!!,
            name = userEntity.name,
            email = userEntity.email,
            isActive = userEntity.isActive,
            createdAt = userEntity.createdAt!!,
            updatedAt = userEntity.updatedAt!!,
        )
    }
} 
