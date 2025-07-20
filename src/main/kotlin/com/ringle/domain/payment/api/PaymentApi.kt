package com.ringle.domain.payment.api

import com.ringle.domain.payment.dto.request.PaymentRequest
import com.ringle.domain.payment.dto.response.PaymentResponse
import com.ringle.global.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "Payment API", description = "결제 관리 API")
@RequestMapping("/api/payments")
interface PaymentApi {

    @PostMapping
    @Operation(
        summary = "결제 처리",
        description = "사용자가 멤버십에 대해 결제를 진행합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "201",
                description = "결제 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "400",
                description = "잘못된 요청 데이터",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "404",
                description = "사용자 또는 멤버십을 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "402",
                description = "결제 실패",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun processPayment(@Valid @RequestBody request: PaymentRequest): ApiResponse<PaymentResponse>

    @GetMapping("/{paymentId}")
    @Operation(
        summary = "결제 조회",
        description = "특정 결제 정보를 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "결제 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "404",
                description = "결제를 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getPayment(
        @Parameter(description = "결제 ID", example = "PAY-1234567890-abc12345")
        @PathVariable paymentId: String
    ): ApiResponse<PaymentResponse>

    @GetMapping("/users/{userId}")
    @Operation(
        summary = "사용자 결제 내역 조회",
        description = "특정 사용자의 모든 결제 내역을 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "사용자 결제 내역 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getUserPayments(
        @Parameter(description = "사용자 ID", example = "1")
        @PathVariable userId: Long
    ): ApiResponse<List<PaymentResponse>>
} 