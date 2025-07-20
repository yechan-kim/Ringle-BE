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

    fun canUseConversation(): Boolean = isActive && 
        (membershipEntity.conversationLimit == -1 || conversationUsed < membershipEntity.conversationLimit)

    fun canUseAnalysis(): Boolean = isActive && 
        (membershipEntity.analysisLimit == -1 || analysisUsed < membershipEntity.analysisLimit)

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

        fun updateConversationUsage(existing: UserMembershipEntity): UserMembershipEntity {
            // 무제한(-1)인 경우 사용 횟수를 증가시키지 않음
            val newConversationUsed = if (existing.membershipEntity.conversationLimit == -1) {
                existing.conversationUsed
            } else {
                existing.conversationUsed + 1
            }

            return UserMembershipEntity(
                id = existing.id,
                userEntity = existing.userEntity,
                membershipEntity = existing.membershipEntity,
                startDate = existing.startDate,
                endDate = existing.endDate,
                conversationUsed = newConversationUsed,
                analysisUsed = existing.analysisUsed,
                isActive = existing.isActive
            )
        }

        fun updateAnalysisUsage(existing: UserMembershipEntity): UserMembershipEntity {
            // 무제한(-1)인 경우 사용 횟수를 증가시키지 않음
            val newAnalysisUsed = if (existing.membershipEntity.analysisLimit == -1) {
                existing.analysisUsed
            } else {
                existing.analysisUsed + 1
            }

            return UserMembershipEntity(
                id = existing.id,
                userEntity = existing.userEntity,
                membershipEntity = existing.membershipEntity,
                startDate = existing.startDate,
                endDate = existing.endDate,
                conversationUsed = existing.conversationUsed,
                analysisUsed = newAnalysisUsed,
                isActive = existing.isActive
            )
        }

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
