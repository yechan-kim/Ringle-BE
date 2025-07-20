package com.ringle.domain.membership.domain.enums

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "멤버십 타입")
enum class MembershipType {
    @Schema(description = "개인 고객용")
    B2C,

    @Schema(description = "기업 고객용")
    B2B;

    companion object {
        fun fromString(value: String): MembershipType {
            return try {
                valueOf(value.uppercase())
            } catch (_: IllegalArgumentException) {
                throw IllegalArgumentException(
                    "Invalid membership type: $value. Valid types are: ${
                        MembershipType.entries.joinToString(
                            ", "
                        ) { it.name }
                    }"
                )
            }
        }
    }
} 
