package com.ringle.domain.membership.domain.entity

import com.ringle.domain.membership.domain.enums.MembershipType
import com.ringle.domain.membership.dto.request.CreateMembershipRequest
import com.ringle.domain.membership.dto.request.UpdateMembershipRequest
import com.ringle.global.common.entity.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "memberships")
class MembershipEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: MembershipType,

    @Column(nullable = false)
    val price: Long,

    @Column(nullable = false)
    val durationDays: Int,

    @Column(nullable = false)
    val conversationLimit: Int,

    @Column(nullable = false)
    val analysisLimit: Int,

    @Column(nullable = false)
    val isActive: Boolean = true
) : BaseTimeEntity() {

    companion object {
        fun from(request: CreateMembershipRequest): MembershipEntity = MembershipEntity(
            name = request.name,
            description = request.description,
            type = request.getMembershipType(),
            price = request.price,
            durationDays = request.durationDays,
            conversationLimit = request.conversationLimit,
            analysisLimit = request.analysisLimit,
            isActive = true
        )

        fun update(existing: MembershipEntity, request: UpdateMembershipRequest): MembershipEntity = MembershipEntity(
            id = existing.id,
            name = request.name ?: existing.name,
            description = request.description ?: existing.description,
            type = request.getMembershipType() ?: existing.type,
            price = request.price ?: existing.price,
            durationDays = request.durationDays ?: existing.durationDays,
            conversationLimit = request.conversationLimit ?: existing.conversationLimit,
            analysisLimit = request.analysisLimit ?: existing.analysisLimit,
            isActive = request.isActive ?: existing.isActive
        )

        fun deactivate(existing: MembershipEntity): MembershipEntity = MembershipEntity(
            id = existing.id,
            name = existing.name,
            description = existing.description,
            type = existing.type,
            price = existing.price,
            durationDays = existing.durationDays,
            conversationLimit = existing.conversationLimit,
            analysisLimit = existing.analysisLimit,
            isActive = false
        )
    }
}
