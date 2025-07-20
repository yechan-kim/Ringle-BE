package com.ringle.domain.user.domain.entity

import com.ringle.domain.membership.domain.entity.MembershipEntity
import com.ringle.global.common.entity.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_memberships")
class UserMembershipEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val userEntity: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id", nullable = false)
    val membershipEntity: MembershipEntity,

    @Column(nullable = false)
    val startDate: LocalDateTime,

    @Column(nullable = false)
    val endDate: LocalDateTime,

    @Column(nullable = false)
    val conversationUsed: Int = 0,

    @Column(nullable = false)
    val analysisUsed: Int = 0,

    @Column(nullable = false)
    val isActive: Boolean = true
) : BaseTimeEntity() {

    fun canUseConversation(): Boolean = isActive && conversationUsed < membershipEntity.conversationLimit

    fun canUseAnalysis(): Boolean = isActive && analysisUsed < membershipEntity.analysisLimit

    companion object {
        fun from(
            userEntity: UserEntity,
            membershipEntity: MembershipEntity,
            startDate: LocalDateTime,
            endDate: LocalDateTime
        ): UserMembershipEntity = UserMembershipEntity(
            userEntity = userEntity,
            membershipEntity = membershipEntity,
            startDate = startDate,
            endDate = endDate,
            conversationUsed = 0,
            analysisUsed = 0,
            isActive = true
        )

        fun updateConversationUsage(existing: UserMembershipEntity): UserMembershipEntity = UserMembershipEntity(
            id = existing.id,
            userEntity = existing.userEntity,
            membershipEntity = existing.membershipEntity,
            startDate = existing.startDate,
            endDate = existing.endDate,
            conversationUsed = existing.conversationUsed + 1,
            analysisUsed = existing.analysisUsed,
            isActive = existing.isActive
        )

        fun updateAnalysisUsage(existing: UserMembershipEntity): UserMembershipEntity = UserMembershipEntity(
            id = existing.id,
            userEntity = existing.userEntity,
            membershipEntity = existing.membershipEntity,
            startDate = existing.startDate,
            endDate = existing.endDate,
            conversationUsed = existing.conversationUsed,
            analysisUsed = existing.analysisUsed + 1,
            isActive = existing.isActive
        )

        fun deactivate(existing: UserMembershipEntity): UserMembershipEntity = UserMembershipEntity(
            id = existing.id,
            userEntity = existing.userEntity,
            membershipEntity = existing.membershipEntity,
            startDate = existing.startDate,
            endDate = existing.endDate,
            conversationUsed = existing.conversationUsed,
            analysisUsed = existing.analysisUsed,
            isActive = false
        )
    }
} 
