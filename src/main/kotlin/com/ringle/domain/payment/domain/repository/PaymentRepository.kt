package com.ringle.domain.payment.domain.repository

import com.ringle.domain.payment.domain.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<PaymentEntity, Long> {
    fun findByPaymentId(paymentId: String): PaymentEntity?
    fun findByUserEntityIdAndIsActiveTrue(userId: Long): List<PaymentEntity>
} 
