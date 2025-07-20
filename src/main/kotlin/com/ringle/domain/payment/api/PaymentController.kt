package com.ringle.domain.payment.api

import com.ringle.domain.payment.dto.request.PaymentRequest
import com.ringle.domain.payment.dto.response.PaymentResponse
import com.ringle.domain.payment.service.PaymentService
import com.ringle.global.common.response.ApiResponse
import com.ringle.global.common.response.type.SuccessCode
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(
    private val paymentService: PaymentService
) : PaymentApi {

    override fun processPayment(@Valid @RequestBody request: PaymentRequest): ApiResponse<PaymentResponse> {
        val payment = paymentService.processPayment(request)
        return ApiResponse.Companion.success(SuccessCode.PAYMENT_COMPLETED, payment)
    }

    override fun getPayment(@PathVariable paymentId: String): ApiResponse<PaymentResponse> {
        val payment = paymentService.getPayment(paymentId)
        return ApiResponse.Companion.success(SuccessCode.REQUEST_OK, payment)
    }

    override fun getUserPayments(@PathVariable userId: Long): ApiResponse<List<PaymentResponse>> {
        val payments = paymentService.getUserPayments(userId)
        return ApiResponse.Companion.success(SuccessCode.REQUEST_OK, payments)
    }
}
