package com.ringle.domain.user.dto.request

import com.ringle.domain.payment.domain.enums.FeatureType
import jakarta.validation.constraints.NotNull

data class UseFeatureRequest(
    @field:NotNull(message = "사용자 ID는 필수입니다")
    val userId: Long,

    @field:NotNull(message = "기능 타입은 필수입니다")
    val featureType: String
) {
    fun getFeatureType(): FeatureType = FeatureType.fromString(featureType)
}
