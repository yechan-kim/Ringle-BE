package com.ringle.domain.payment.service

import com.ringle.domain.membership.domain.repository.MembershipRepository
import com.ringle.domain.payment.domain.entity.PaymentEntity
import com.ringle.domain.payment.domain.repository.PaymentRepository
import com.ringle.domain.payment.dto.request.PaymentRequest
import com.ringle.domain.payment.dto.response.PaymentResponse
import com.ringle.domain.user.domain.entity.UserMembershipEntity
import com.ringle.domain.user.domain.repository.UserMembershipRepository
import com.ringle.domain.user.domain.repository.UserRepository
import com.ringle.domain.user.dto.response.UserMembershipResponse
import com.ringle.global.common.response.type.ErrorCode
import com.ringle.global.exception.ServiceException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val userRepository: UserRepository,
    private val membershipRepository: MembershipRepository,
    private val userMembershipRepository: UserMembershipRepository,
    private val paymentMockService: PaymentMockService
) {

    fun processPayment(request: PaymentRequest): PaymentResponse {
        // 사용자 조회
        val user = userRepository.findById(request.userId)
            .orElseThrow { ServiceException(ErrorCode.USER_NOT_FOUND) }

        // 멤버십 조회
        val membership = membershipRepository.findById(request.membershipId)
            .orElseThrow { ServiceException(ErrorCode.MEMBERSHIP_NOT_FOUND) }

        if (!membership.isActive) {
            throw ServiceException(ErrorCode.MEMBERSHIP_INACTIVE)
        }

        // PG사 결제 처리
        val paymentResult = paymentMockService.processPayment()

        if (!paymentResult.success) {
            throw ServiceException(ErrorCode.PAYMENT_FAILED)
        }

        // 결제 정보 저장
        val paymentEntity = PaymentEntity.from(
            request = request,
            userEntity = user,
            membershipEntity = membership,
            transactionId = paymentResult.transactionId!!
        )

        val savedPayment = paymentRepository.save(paymentEntity)

        // 사용자 멤버십 생성
        val startDate = LocalDateTime.now()
        val endDate = startDate.plusDays(membership.durationDays.toLong())

        val userMembershipEntity = UserMembershipEntity.from(
            userEntity = user,
            membershipEntity = membership,
            startDate = startDate,
            endDate = endDate
        )

        val savedUserMembership = userMembershipRepository.save(userMembershipEntity)

        return PaymentResponse.of(
            savedPayment,
            UserMembershipResponse.from(savedUserMembership)
        )
    }

    @Transactional(readOnly = true)
    fun getPayment(paymentId: String): PaymentResponse {
        val payment = paymentRepository.findByPaymentId(paymentId)
            ?: throw ServiceException(ErrorCode.PAYMENT_NOT_FOUND)

        val userMembership = userMembershipRepository.findByUserEntityIdAndIsActiveTrue(payment.userEntity.id!!)
            .firstOrNull { it.membershipEntity.id == payment.membershipEntity.id }
            ?: throw ServiceException(ErrorCode.USER_MEMBERSHIP_NOT_FOUND)

        return PaymentResponse.of(
            payment,
            UserMembershipResponse.from(userMembership)
        )
    }

    @Transactional(readOnly = true)
    fun getUserPayments(userId: Long): List<PaymentResponse> {
        val payments = paymentRepository.findByUserEntityIdAndIsActiveTrue(userId)

        return payments.map { payment ->
            val userMembership = userMembershipRepository.findByUserEntityIdAndIsActiveTrue(payment.userEntity.id!!)
                .firstOrNull { it.membershipEntity.id == payment.membershipEntity.id }
                ?: throw ServiceException(ErrorCode.USER_MEMBERSHIP_NOT_FOUND)

            PaymentResponse.of(
                payment,
                UserMembershipResponse.from(userMembership)
            )
        }
    }
} 
