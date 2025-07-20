package com.ringle.domain.payment.service

import com.ringle.domain.payment.dto.response.PaymentResult
import org.springframework.stereotype.Service
import java.util.*

@Service
class PaymentMockService {

    fun processPayment(): PaymentResult {
        return try {
            PaymentResult.success(
                transactionId = generateTransactionId(),
            )
        } catch (e: Exception) {
            PaymentResult.failure(
                "결제 처리 중 오류가 발생했습니다: ${e.message}",
            )
        }
    }

    private fun generateTransactionId(): String =
        "TXN-${System.currentTimeMillis()}-${UUID.randomUUID().toString().substring(0, 8)}"
}
