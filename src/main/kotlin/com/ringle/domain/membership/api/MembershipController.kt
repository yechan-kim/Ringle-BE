package com.ringle.domain.membership.api

import com.ringle.domain.membership.domain.enums.MembershipType
import com.ringle.domain.membership.dto.request.CreateMembershipRequest
import com.ringle.domain.membership.dto.request.UpdateMembershipRequest
import com.ringle.domain.membership.dto.response.MembershipResponse
import com.ringle.domain.membership.service.MembershipService
import com.ringle.global.common.response.ApiResponse
import com.ringle.global.common.response.type.SuccessCode
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MembershipController(
    private val membershipService: MembershipService
) : MembershipApi {

    override fun createMembership(@Valid @RequestBody request: CreateMembershipRequest): ApiResponse<MembershipResponse> {
        val membership = membershipService.createMembership(request)
        return ApiResponse.success(SuccessCode.MEMBERSHIP_CREATED, membership)
    }

    override fun getMembership(@PathVariable id: Long): ApiResponse<MembershipResponse> {
        val membership = membershipService.getMembership(id)
        return ApiResponse.success(SuccessCode.REQUEST_OK, membership)
    }

    override fun getAllMemberships(): ApiResponse<List<MembershipResponse>> {
        val memberships = membershipService.getAllMemberships()
        return ApiResponse.success(SuccessCode.REQUEST_OK, memberships)
    }

    override fun getMembershipsByType(@PathVariable type: MembershipType): ApiResponse<List<MembershipResponse>> {
        val memberships = membershipService.getMembershipsByType(type)
        return ApiResponse.success(SuccessCode.REQUEST_OK, memberships)
    }

    override fun updateMembership(
        @PathVariable id: Long,
        @RequestBody request: UpdateMembershipRequest
    ): ApiResponse<MembershipResponse> {
        val membership = membershipService.updateMembership(id, request)
        return ApiResponse.success(SuccessCode.MEMBERSHIP_UPDATED, membership)
    }

    override fun deleteMembership(@PathVariable id: Long): ApiResponse<Unit> {
        membershipService.deleteMembership(id)
        return ApiResponse.success(SuccessCode.MEMBERSHIP_DELETED)
    }
}
