package com.ringle.domain.user.domain.repository

import com.ringle.domain.user.domain.entity.UserMembershipEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface UserMembershipRepository : JpaRepository<UserMembershipEntity, Long> {
    fun findByUserEntityIdAndIsActiveTrue(userId: Long): List<UserMembershipEntity>

    @Query("SELECT um FROM UserMembershipEntity um WHERE um.userEntity.id = :userId AND um.isActive = true AND um.endDate > :now")
    fun findActiveMembershipsByUserId(@Param("userId") userId: Long, @Param("now") now: LocalDateTime = LocalDateTime.now()): List<UserMembershipEntity>
} 