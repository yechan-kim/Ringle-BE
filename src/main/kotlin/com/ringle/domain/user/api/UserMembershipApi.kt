package com.ringle.domain.user.api

import com.ringle.domain.user.dto.request.AssignMembershipRequest
import com.ringle.domain.user.dto.request.UseFeatureRequest
import com.ringle.domain.user.dto.response.UserFeatureResponse
import com.ringle.domain.user.dto.response.UserMembershipResponse
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

@Tag(name = "User Membership API", description = "사용자 멤버십 관리 API")
@RequestMapping("/api/user-memberships")
interface UserMembershipApi {

    @PostMapping
    @Operation(
        summary = "멤버십 할당",
        description = "사용자에게 멤버십을 할당합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "201",
                description = "멤버십 할당 성공",
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
    fun assignMembership(@Valid @RequestBody request: AssignMembershipRequest): ApiResponse<UserMembershipResponse>

    @GetMapping("/users/{userId}")
    @Operation(
        summary = "사용자 멤버십 조회",
        description = "특정 사용자의 모든 멤버십을 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "사용자 멤버십 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getUserMemberships(
        @Parameter(description = "사용자 ID", example = "1")
        @PathVariable userId: Long
    ): ApiResponse<List<UserMembershipResponse>>

    @GetMapping("/users/{userId}/active")
    @Operation(
        summary = "활성 사용자 멤버십 조회",
        description = "특정 사용자의 활성 멤버십을 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "활성 사용자 멤버십 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getActiveUserMemberships(
        @Parameter(description = "사용자 ID", example = "1")
        @PathVariable userId: Long
    ): ApiResponse<List<UserMembershipResponse>>

    @PostMapping("/use-feature")
    @Operation(
        summary = "멤버십 기능 사용",
        description = "멤버십 기능을 사용합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "기능 사용 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "400",
                description = "잘못된 요청 데이터",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "403",
                description = "사용 가능한 기능이 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun useFeature(@Valid @RequestBody request: UseFeatureRequest): ApiResponse<UserFeatureResponse>

    @DeleteMapping("/{userMembershipId}")
    @Operation(
        summary = "사용자 멤버십 비활성화",
        description = "특정 사용자 멤버십을 비활성화합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "사용자 멤버십 비활성화 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "404",
                description = "사용자 멤버십을 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun deactivateUserMembership(
        @Parameter(description = "사용자 멤버십 ID", example = "1")
        @PathVariable userMembershipId: Long
    ): ApiResponse<Unit>
} 
