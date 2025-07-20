package com.ringle.domain.membership.dto.request

import com.ringle.domain.membership.domain.enums.MembershipType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class UpdateMembershipRequest(
    @field:NotBlank(message = "멤버십 이름은 필수입니다")
    val name: String? = null,

    @field:NotBlank(message = "멤버십 설명은 필수입니다")
    val description: String? = null,

    @field:NotBlank(message = "멤버십 타입은 필수입니다")
    val type: String? = null,

    @field:NotNull(message = "멤버십 가격은 필수입니다")
    @field:Positive(message = "멤버십 가격은 0보다 커야 합니다")
    val price: Long? = null,

    @field:NotNull(message = "이용 기간은 필수입니다")
    @field:Positive(message = "이용 기간은 0보다 커야 합니다")
    val durationDays: Int? = null,

    @field:NotNull(message = "대화 제한 횟수는 필수입니다")
    val conversationLimit: Int? = null,

    @field:NotNull(message = "분석 제한 횟수는 필수입니다")
    val analysisLimit: Int? = null,

    val isActive: Boolean? = null
) {
    fun getMembershipType(): MembershipType? = type?.let { MembershipType.fromString(it) }
}
