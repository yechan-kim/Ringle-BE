package com.ringle.domain.user.api

import com.ringle.domain.user.dto.request.AssignMembershipRequest
import com.ringle.domain.user.dto.request.UseFeatureRequest
import com.ringle.domain.user.dto.response.UserFeatureResponse
import com.ringle.domain.user.dto.response.UserMembershipResponse
import com.ringle.domain.user.service.UserMembershipService
import com.ringle.global.common.response.ApiResponse
import com.ringle.global.common.response.type.SuccessCode
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserMembershipController(
    private val userMembershipService: UserMembershipService
) : UserMembershipApi {

    override fun assignMembership(@Valid @RequestBody request: AssignMembershipRequest): ApiResponse<UserMembershipResponse> {
        val userMembership = userMembershipService.assignMembership(request)
        return ApiResponse.success(SuccessCode.MEMBERSHIP_ASSIGNED, userMembership)
    }

    override fun getUserMemberships(@PathVariable userId: Long): ApiResponse<List<UserMembershipResponse>> {
        val userMemberships = userMembershipService.getUserMemberships(userId)
        return ApiResponse.success(SuccessCode.REQUEST_OK, userMemberships)
    }

    override fun getActiveUserMemberships(@PathVariable userId: Long): ApiResponse<List<UserMembershipResponse>> {
        val userMemberships = userMembershipService.getActiveUserMemberships(userId)
        return ApiResponse.success(SuccessCode.REQUEST_OK, userMemberships)
    }

    override fun useFeature(@Valid @RequestBody request: UseFeatureRequest): ApiResponse<UserFeatureResponse> {
        val result = userMembershipService.useFeature(request)
        return ApiResponse.success(SuccessCode.FEATURE_USED, result)
    }

    override fun deactivateUserMembership(@PathVariable userMembershipId: Long): ApiResponse<Unit> {
        userMembershipService.deactivateUserMembership(userMembershipId)
        return ApiResponse.success(SuccessCode.MEMBERSHIP_DEACTIVATED)
    }
} 
