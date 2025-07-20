package com.ringle.domain.payment.domain.entity

import com.ringle.domain.payment.dto.request.PaymentRequest
import com.ringle.domain.user.domain.entity.UserEntity
import com.ringle.domain.membership.domain.entity.MembershipEntity
import com.ringle.domain.payment.domain.enums.PaymentMethod
import com.ringle.global.common.entity.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "payments")
class PaymentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val paymentId: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val userEntity: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id", nullable = false)
    val membershipEntity: MembershipEntity,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val paymentMethod: PaymentMethod,

    @Column(nullable = false, unique = true)
    val transactionId: String,

    @Column(nullable = false)
    val paidAt: LocalDateTime,

    @Column(nullable = false)
    val isActive: Boolean = true
) : BaseTimeEntity() {

    companion object {
        fun from(
            request: PaymentRequest,
            userEntity: UserEntity,
            membershipEntity: MembershipEntity,
            transactionId: String,
        ): PaymentEntity = PaymentEntity(
            paymentId = generatePaymentId(),
            userEntity = userEntity,
            membershipEntity = membershipEntity,
            paymentMethod = PaymentMethod.fromString(request.paymentMethod),
            transactionId = transactionId,
            paidAt = LocalDateTime.now(),
            isActive = true
        )

        private fun generatePaymentId(): String =
            "PAY-${System.currentTimeMillis()}-${UUID.randomUUID().toString().substring(0, 8)}"

    }
} 
