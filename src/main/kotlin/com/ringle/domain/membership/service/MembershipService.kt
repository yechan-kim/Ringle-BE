package com.ringle.domain.membership.service

import com.ringle.domain.membership.domain.entity.MembershipEntity
import com.ringle.domain.membership.domain.enums.MembershipType
import com.ringle.domain.membership.domain.repository.MembershipRepository
import com.ringle.domain.membership.dto.request.CreateMembershipRequest
import com.ringle.domain.membership.dto.request.UpdateMembershipRequest
import com.ringle.domain.membership.dto.response.MembershipResponse
import com.ringle.global.common.response.type.ErrorCode
import com.ringle.global.exception.ServiceException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MembershipService(
    private val membershipRepository: MembershipRepository
) {

    fun createMembership(request: CreateMembershipRequest): MembershipResponse {
        // 이름 중복 체크
        membershipRepository.findByName(request.name)?.let {
            throw ServiceException(ErrorCode.MEMBERSHIP_NAME_DUPLICATE)
        }

        return membershipRepository.save(MembershipEntity.from(request))
            .let { MembershipResponse.Companion.from(it) }
    }

    @Transactional(readOnly = true)
    fun getMembership(id: Long): MembershipResponse {
        val membership = membershipRepository.findById(id)
            .orElseThrow { ServiceException(ErrorCode.MEMBERSHIP_NOT_FOUND) }

        return MembershipResponse.Companion.from(membership)
    }

    @Transactional(readOnly = true)
    fun getAllMemberships(): List<MembershipResponse> =
        membershipRepository.findByIsActiveTrue()
            .map { MembershipResponse.Companion.from(it) }

    @Transactional(readOnly = true)
    fun getMembershipsByType(type: MembershipType): List<MembershipResponse> =
        membershipRepository.findByTypeAndIsActiveTrue(type)
            .map { MembershipResponse.Companion.from(it) }

    fun updateMembership(id: Long, request: UpdateMembershipRequest): MembershipResponse {
        val membership = membershipRepository.findById(id)
            .orElseThrow { ServiceException(ErrorCode.MEMBERSHIP_NOT_FOUND) }

        // 이름 변경 시 중복 체크
        request.name?.let { newName ->
            if (newName != membership.name) {
                membershipRepository.findByName(newName)?.let {
                    throw ServiceException(ErrorCode.MEMBERSHIP_NAME_DUPLICATE)
                }
            }
        }

        return membershipRepository.save(MembershipEntity.update(membership, request))
            .let { MembershipResponse.Companion.from(it) }
    }

    fun deleteMembership(id: Long) {
        val membership = membershipRepository.findById(id)
            .orElseThrow { ServiceException(ErrorCode.MEMBERSHIP_NOT_FOUND) }

        membershipRepository.save(MembershipEntity.deactivate(membership))
    }
}
