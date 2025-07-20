package com.ringle.domain.payment.domain.enums

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "기능 타입")
enum class FeatureType {
    @Schema(description = "대화 기능")
    CONVERSATION,

    @Schema(description = "분석 기능")
    ANALYSIS;

    companion object {
        fun fromString(value: String): FeatureType {
            return try {
                valueOf(value.uppercase())
            } catch (_: IllegalArgumentException) {
                throw IllegalArgumentException(
                    "Invalid feature type: $value. Valid types are: ${
                        FeatureType.entries.joinToString(
                            ", "
                        ) { it.name }
                    }"
                )
            }
        }
    }
}
