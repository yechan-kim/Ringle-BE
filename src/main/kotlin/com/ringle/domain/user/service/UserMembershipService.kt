package com.ringle.domain.user.service

import com.ringle.domain.payment.domain.enums.FeatureType
import com.ringle.domain.membership.domain.repository.MembershipRepository
import com.ringle.domain.user.domain.entity.UserMembershipEntity
import com.ringle.domain.user.domain.repository.UserMembershipRepository
import com.ringle.domain.user.domain.repository.UserRepository
import com.ringle.domain.user.dto.request.AssignMembershipRequest
import com.ringle.domain.user.dto.request.UseFeatureRequest
import com.ringle.domain.user.dto.response.UserFeatureResponse
import com.ringle.domain.user.dto.response.UserMembershipResponse
import com.ringle.global.common.response.type.ErrorCode
import com.ringle.global.exception.ServiceException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserMembershipService(
    private val userMembershipRepository: UserMembershipRepository,
    private val membershipRepository: MembershipRepository,
    private val userRepository: UserRepository
) {

    fun assignMembership(request: AssignMembershipRequest): UserMembershipResponse {
        val user = userRepository.findById(request.userId)
            .orElseThrow { ServiceException(ErrorCode.USER_NOT_FOUND) }

        val membership = membershipRepository.findById(request.membershipId)
            .orElseThrow { ServiceException(ErrorCode.MEMBERSHIP_NOT_FOUND) }

        if (!membership.isActive) {
            throw ServiceException(ErrorCode.MEMBERSHIP_INACTIVE)
        }

        val startDate = LocalDateTime.now()
        val endDate = startDate.plusDays(membership.durationDays.toLong())

        val userMembershipEntity = UserMembershipEntity.from(
            userEntity = user,
            membershipEntity = membership,
            startDate = startDate,
            endDate = endDate
        )

        return userMembershipRepository.save(userMembershipEntity).let { UserMembershipResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getUserMemberships(userId: Long): List<UserMembershipResponse> =
        userMembershipRepository.findByUserEntityIdAndIsActiveTrue(userId)
            .map { UserMembershipResponse.from(it) }

    @Transactional(readOnly = true)
    fun getActiveUserMemberships(userId: Long): List<UserMembershipResponse> =
        userMembershipRepository.findActiveMembershipsByUserId(userId)
            .map { UserMembershipResponse.from(it) }

    fun useFeature(request: UseFeatureRequest): UserFeatureResponse {
        val activeMemberships = userMembershipRepository.findActiveMembershipsByUserId(request.userId)

        if (activeMemberships.isEmpty()) {
            throw ServiceException(ErrorCode.NO_ACTIVE_MEMBERSHIP)
        }

        val featureType = request.getFeatureType()

        // 사용 가능한 멤버십 찾기
        val availableMembership = when (featureType) {
            FeatureType.CONVERSATION -> activeMemberships.find { it.canUseConversation() }
            FeatureType.ANALYSIS -> activeMemberships.find { it.canUseAnalysis() }
        } ?: throw ServiceException(ErrorCode.FEATURE_NOT_AVAILABLE)

        val updatedMembership = when (featureType) {
            FeatureType.CONVERSATION -> UserMembershipEntity.updateConversationUsage(availableMembership)
            FeatureType.ANALYSIS -> UserMembershipEntity.updateAnalysisUsage(availableMembership)
        }

        userMembershipRepository.save(updatedMembership)
        return UserFeatureResponse.of(
            featureType,
            updatedMembership
        )
    }

    fun deactivateUserMembership(userMembershipId: Long) {
        val userMembership = userMembershipRepository.findById(userMembershipId)
            .orElseThrow { ServiceException(ErrorCode.USER_MEMBERSHIP_NOT_FOUND) }

        userMembershipRepository.save(UserMembershipEntity.deactivate(userMembership))
    }
} 
