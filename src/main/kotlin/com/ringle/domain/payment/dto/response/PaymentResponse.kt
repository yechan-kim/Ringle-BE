package com.ringle.domain.payment.dto.response

import com.ringle.domain.payment.domain.entity.PaymentEntity
import com.ringle.domain.payment.domain.enums.PaymentMethod
import com.ringle.domain.user.dto.response.UserMembershipResponse
import java.time.LocalDateTime

data class PaymentResponse(
    val paymentId: String,
    val userId: Long,
    val membershipId: Long,
    val amount: Long,
    val paymentMethod: PaymentMethod,
    val transactionId: String,
    val paidAt: LocalDateTime,
    val userMembership: UserMembershipResponse
) {
    companion object {
        fun of(
            paymentEntity: PaymentEntity,
            userMembershipResponse: UserMembershipResponse
        ): PaymentResponse =
            PaymentResponse(
                paymentId = paymentEntity.paymentId,
                userId = paymentEntity.userEntity.id!!,
                membershipId = paymentEntity.membershipEntity.id!!,
                amount = paymentEntity.membershipEntity.price,
                paymentMethod = paymentEntity.paymentMethod,
                transactionId = paymentEntity.transactionId,
                paidAt = paymentEntity.paidAt,
                userMembership = userMembershipResponse
            )
    }
}
