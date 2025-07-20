package com.ringle.domain.user.api

import com.ringle.domain.user.dto.request.CreateUserRequest
import com.ringle.domain.user.dto.response.UserResponse
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

@Tag(name = "User API", description = "더미 사용자 관리 API")
@RequestMapping("/api/user")
interface UserApi {

    @PostMapping
    @Operation(
        summary = "사용자 생성",
        description = "새로운 사용자를 생성합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "201",
                description = "사용자 생성 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "400",
                description = "잘못된 요청 데이터",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun createUser(@Valid @RequestBody request: CreateUserRequest): ApiResponse<UserResponse>

    @GetMapping("/{userId}")
    @Operation(
        summary = "사용자 조회",
        description = "특정 사용자를 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "사용자 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "404",
                description = "사용자를 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getUser(
        @Parameter(description = "사용자 ID", example = "1")
        @PathVariable userId: Long
    ): ApiResponse<UserResponse>

    @GetMapping
    @Operation(
        summary = "전체 사용자 조회",
        description = "모든 활성 사용자를 조회합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "전체 사용자 조회 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun getAllUsers(): ApiResponse<List<UserResponse>>

    @DeleteMapping("/{userId}")
    @Operation(
        summary = "사용자 삭제",
        description = "특정 사용자를 삭제합니다."
    )
    @ApiResponses(
        value = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "사용자 삭제 성공",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            ),
            SwaggerApiResponse(
                responseCode = "404",
                description = "사용자를 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ApiResponse::class))]
            )
        ]
    )
    fun deleteUser(
        @Parameter(description = "사용자 ID", example = "1")
        @PathVariable userId: Long
    ): ApiResponse<Unit>
} 
