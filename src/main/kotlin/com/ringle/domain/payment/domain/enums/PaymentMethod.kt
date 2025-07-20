package com.ringle.domain.payment.domain.enums

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "결제 방법")
enum class PaymentMethod {
    @Schema(description = "신용카드")
    CREDIT_CARD,

    @Schema(description = "체크카드")
    CHECK_CARD,

    @Schema(description = "계좌이체")
    BANK_TRANSFER,

    @Schema(description = "간편결제")
    EASY_PAY;

    companion object {
        fun fromString(value: String): PaymentMethod {
            return try {
                valueOf(value.uppercase())
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException(
                    "Invalid payment method: $value. Valid methods are: ${
                        PaymentMethod.entries.joinToString(
                            ", "
                        ) { it.name }
                    }"
                )
            }
        }
    }
}
