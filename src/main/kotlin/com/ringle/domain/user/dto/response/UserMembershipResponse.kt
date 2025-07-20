package com.ringle.domain.user.dto.response

import com.ringle.domain.user.domain.entity.UserMembershipEntity
import java.time.LocalDateTime

data class UserMembershipResponse(
    val id: Long,
    val userId: Long,
    val membershipId: Long,
    val membershipName: String,
    val startDate: String,
    val endDate: String,
    val conversationUsed: Int,
    val analysisUsed: Int,
    val conversationLimit: Int,
    val analysisLimit: Int,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(userMembershipEntity: UserMembershipEntity): UserMembershipResponse = UserMembershipResponse(
            id = userMembershipEntity.id!!,
            userId = userMembershipEntity.userEntity.id!!,
            membershipId = userMembershipEntity.membershipEntity.id!!,
            membershipName = userMembershipEntity.membershipEntity.name,
            startDate = userMembershipEntity.startDate.toString(),
            endDate = userMembershipEntity.endDate.toString(),
            conversationUsed = userMembershipEntity.conversationUsed,
            analysisUsed = userMembershipEntity.analysisUsed,
            conversationLimit = userMembershipEntity.membershipEntity.conversationLimit,
            analysisLimit = userMembershipEntity.membershipEntity.analysisLimit,
            isActive = userMembershipEntity.isActive,
            createdAt = userMembershipEntity.createdAt!!,
            updatedAt = userMembershipEntity.updatedAt!!,
        )
    }
}
