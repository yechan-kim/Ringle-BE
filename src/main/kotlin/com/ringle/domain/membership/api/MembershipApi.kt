package com.ringle.domain.membership.api

import com.ringle.domain.membership.domain.enums.MembershipType
import com.ringle.domain.membership.dto.request.CreateMembershipRequest
import com.ringle.domain.membership.dto.request.UpdateMembershipRequest
import com.ringle.domain.membership.dto.response.MembershipResponse
import com.ringle.global.common.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse

@Tag(name = "Membership API", description = "멤버십 관리 API")
@RequestMapping("/api/memberships")
interface MembershipApi {

    @PostMapping
    @Operation(
        summary = "멤버십 생성",
        description = "새로운 멤버십을 생성합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "201",
                description = "멤버십 생성 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "400",
                description = "잘못된 요청 데이터",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun createMembership(@Valid @RequestBody request: CreateMembershipRequest): ApiResponse<MembershipResponse>

    @GetMapping("/{id}")
    @Operation(
        summary = "멤버십 조회",
        description = "특정 ID의 멤버십을 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "멤버십 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "404",
                description = "멤버십을 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getMembership(
        @Parameter(description = "멤버십 ID", example = "1")
        @PathVariable id: Long
    ): ApiResponse<MembershipResponse>

    @GetMapping
    @Operation(
        summary = "전체 멤버십 조회",
        description = "모든 멤버십을 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "전체 멤버십 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getAllMemberships(): ApiResponse<List<MembershipResponse>>

    @GetMapping("/type/{type}")
    @Operation(
        summary = "타입별 멤버십 조회",
        description = "특정 타입의 멤버십들을 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "타입별 멤버십 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getMembershipsByType(
        @Parameter(description = "멤버십 타입", example = "BASIC")
        @PathVariable type: MembershipType
    ): ApiResponse<List<MembershipResponse>>

    @PutMapping("/{id}")
    @Operation(
        summary = "멤버십 수정",
        description = "기존 멤버십을 수정합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "멤버십 수정 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "400",
                description = "잘못된 요청 데이터",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "404",
                description = "멤버십을 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun updateMembership(
        @Parameter(description = "멤버십 ID", example = "1")
        @PathVariable id: Long,
        @RequestBody request: UpdateMembershipRequest
    ): ApiResponse<MembershipResponse>

    @DeleteMapping("/{id}")
    @Operation(
        summary = "멤버십 삭제",
        description = "특정 멤버십을 삭제합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "멤버십 삭제 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "404",
                description = "멤버십을 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun deleteMembership(
        @Parameter(description = "멤버십 ID", example = "1")
        @PathVariable id: Long
    ): ApiResponse<Unit>
}
