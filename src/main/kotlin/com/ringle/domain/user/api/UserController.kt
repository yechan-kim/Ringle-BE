package com.ringle.domain.user.api

import com.ringle.domain.user.dto.request.CreateUserRequest
import com.ringle.domain.user.dto.response.UserResponse
import com.ringle.domain.user.service.UserService
import com.ringle.global.common.response.ApiResponse
import com.ringle.global.common.response.type.SuccessCode
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) : UserApi {

    override fun createUser(@Valid @RequestBody request: CreateUserRequest): ApiResponse<UserResponse> {
        val user = userService.createUser(request)
        return ApiResponse.success(SuccessCode.USER_CREATED, user)
    }

    override fun getUser(@PathVariable userId: Long): ApiResponse<UserResponse> {
        val user = userService.getUser(userId)
        return ApiResponse.success(SuccessCode.REQUEST_OK, user)
    }

    override fun getAllUsers(): ApiResponse<List<UserResponse>> {
        val users = userService.getAllUsers()
        return ApiResponse.success(SuccessCode.REQUEST_OK, users)
    }

    override fun deleteUser(@PathVariable userId: Long): ApiResponse<Unit> {
        userService.deleteUser(userId)
        return ApiResponse.success(SuccessCode.USER_DELETED)
    }
} 
