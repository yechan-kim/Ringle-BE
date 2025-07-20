package com.ringle.domain.payment.dto.request

import com.ringle.domain.payment.domain.enums.PaymentMethod
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class PaymentRequest(
    @field:NotNull(message = "사용자 ID는 필수입니다")
    val userId: Long,

    @field:NotNull(message = "멤버십 ID는 필수입니다")
    val membershipId: Long,

    @field:NotNull(message = "결제 수단은 필수입니다")
    val paymentMethod: String,

    @field:NotNull(message = "카드 번호는 필수입니다")
    val cardNumber: String,

    @field:NotNull(message = "카드 만료일은 필수입니다")
    val cardExpiry: String,

    @field:NotNull(message = "카드 CVC는 필수입니다")
    val cardCvc: String
) {
    fun getPaymentMethod(): PaymentMethod = PaymentMethod.fromString(paymentMethod)
} 
