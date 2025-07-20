package com.ringle.domain.user.dto.response

import com.ringle.domain.payment.domain.enums.FeatureType
import com.ringle.domain.user.domain.entity.UserMembershipEntity

data class UserFeatureResponse(
    val featureType: FeatureType,
    val remainingConversationLimit: Int,
    val remainingAnalysisLimit: Int,
) {
    companion object {
        fun of(
            featureType: FeatureType,
            userMembershipEntity: UserMembershipEntity,
        ): UserFeatureResponse = UserFeatureResponse(
            featureType = featureType,
            remainingConversationLimit = userMembershipEntity.membershipEntity.conversationLimit - userMembershipEntity.conversationUsed,
            remainingAnalysisLimit = userMembershipEntity.membershipEntity.analysisLimit - userMembershipEntity.analysisUsed
        )
    }
}
