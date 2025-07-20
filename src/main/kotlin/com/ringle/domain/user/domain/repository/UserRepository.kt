package com.ringle.domain.user.domain.repository

import com.ringle.domain.user.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun findByIdAndIsActiveTrue(id: Long): UserEntity?
    fun findByIsActiveTrue(): List<UserEntity>
}
