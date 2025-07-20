package com.ringle.domain.user.service

import com.ringle.domain.user.domain.entity.UserEntity
import com.ringle.domain.user.domain.repository.UserRepository
import com.ringle.domain.user.dto.request.CreateUserRequest
import com.ringle.domain.user.dto.response.UserResponse
import com.ringle.global.common.response.type.ErrorCode
import com.ringle.global.exception.ServiceException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(request: CreateUserRequest): UserResponse {
        // 이메일 중복 체크
        userRepository.findByEmail(request.email)?.let {
            throw ServiceException(ErrorCode.USER_EMAIL_DUPLICATE)
        }

        return userRepository.save(UserEntity.from(request))
            .let { UserResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getUser(userId: Long): UserResponse {
        val user = userRepository.findByIdAndIsActiveTrue(userId)
            ?: throw ServiceException(ErrorCode.USER_NOT_FOUND)

        return UserResponse.from(user)
    }

    @Transactional(readOnly = true)
    fun getAllUsers(): List<UserResponse> =
        userRepository.findByIsActiveTrue()
            .map { UserResponse.from(it) }

    fun deleteUser(userId: Long) {
        val user = userRepository.findByIdAndIsActiveTrue(userId)
            ?: throw ServiceException(ErrorCode.USER_NOT_FOUND)

        userRepository.save(UserEntity.deactivate(user))
    }
} 
