package com.ringle.domain.membership.dto.response

import com.ringle.domain.membership.domain.entity.MembershipEntity
import com.ringle.domain.membership.domain.enums.MembershipType
import java.time.LocalDateTime

data class MembershipResponse(
    val id: Long,
    val name: String,
    val description: String,
    val type: MembershipType,
    val price: Long,
    val durationDays: Int,
    val conversationLimit: Int,
    val analysisLimit: Int,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(membershipEntity: MembershipEntity): MembershipResponse = MembershipResponse(
            id = membershipEntity.id!!,
            name = membershipEntity.name,
            description = membershipEntity.description,
            type = membershipEntity.type,
            price = membershipEntity.price,
            durationDays = membershipEntity.durationDays,
            conversationLimit = membershipEntity.conversationLimit,
            analysisLimit = membershipEntity.analysisLimit,
            isActive = membershipEntity.isActive,
            createdAt = membershipEntity.createdAt!!,
            updatedAt = membershipEntity.updatedAt!!
        )
    }
}
