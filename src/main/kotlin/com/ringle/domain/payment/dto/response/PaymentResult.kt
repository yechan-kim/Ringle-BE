package com.ringle.domain.payment.dto.response

data class PaymentResult(
    val success: Boolean,
    val transactionId: String?,
    val message: String,
) {
    companion object {
        fun success(transactionId: String): PaymentResult {
            return PaymentResult(
                success = true,
                transactionId = transactionId,
                message = "결제가 성공적으로 처리되었습니다.",
            )
        }

        fun failure(message: String): PaymentResult {
            return PaymentResult(
                success = false,
                transactionId = null,
                message = message,
            )
        }
    }
}
