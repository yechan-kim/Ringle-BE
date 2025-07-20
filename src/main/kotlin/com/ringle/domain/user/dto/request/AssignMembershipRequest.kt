package com.ringle.domain.user.dto.request

import jakarta.validation.constraints.NotNull

data class AssignMembershipRequest(
    @field:NotNull(message = "사용자 ID는 필수입니다")
    val userId: Long,
    
    @field:NotNull(message = "멤버십 ID는 필수입니다")
    val membershipId: Long
)
