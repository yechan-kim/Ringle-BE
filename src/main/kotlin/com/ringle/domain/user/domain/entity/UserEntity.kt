package com.ringle.domain.user.domain.entity

import com.ringle.domain.user.dto.request.CreateUserRequest
import com.ringle.global.common.entity.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val isActive: Boolean = true
) : BaseTimeEntity() {

    companion object {
        fun from(request: CreateUserRequest): UserEntity = UserEntity(
            name = request.name,
            email = request.email,
            isActive = true
        )

        fun deactivate(existing: UserEntity): UserEntity = UserEntity(
            id = existing.id,
            name = existing.name,
            email = existing.email,
            isActive = false
        )
    }
}
