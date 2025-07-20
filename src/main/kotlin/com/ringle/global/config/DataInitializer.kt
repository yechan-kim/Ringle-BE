package com.ringle.global.config

import com.ringle.domain.membership.domain.entity.MembershipEntity
import com.ringle.domain.membership.domain.enums.MembershipType
import com.ringle.domain.membership.domain.repository.MembershipRepository
import com.ringle.domain.user.domain.entity.UserEntity
import com.ringle.domain.user.domain.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val membershipRepository: MembershipRepository,
    private val userRepository: UserRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        initializeMemberships()
        initializeUsers()
    }

    private fun initializeMemberships() {
        if (membershipRepository.count() == 0L) {
            val memberships = listOf(
                MembershipEntity(
                    name = "기본 멤버십",
                    description = "개인 고객을 위한 기본적인 기능을 제공하는 멤버십",
                    type = MembershipType.B2C,
                    price = 15000L,
                    durationDays = 30,
                    conversationLimit = 10,
                    analysisLimit = 3,
                ),
                MembershipEntity(
                    name = "프리미엄 멤버십",
                    description = "개인 고객을 위한 고급 기능을 제공하는 멤버십",
                    type = MembershipType.B2C,
                    price = 29000L,
                    durationDays = 60,
                    conversationLimit = 20,
                    analysisLimit = 5,
                ),
                MembershipEntity(
                    name = "무제한 멤버십",
                    description = "개인 고객을 위한 무제한 기능을 제공하는 멤버십",
                    type = MembershipType.B2C,
                    price = 50000L,
                    durationDays = 90,
                    conversationLimit = -1, // 무제한
                    analysisLimit = -1, // 무제한
                ),
                MembershipEntity(
                    name = "비즈니스 기본",
                    description = "기업 고객을 위한 기본적인 기능을 제공하는 멤버십",
                    type = MembershipType.B2B,
                    price = 80000L,
                    durationDays = 90,
                    conversationLimit = 50,
                    analysisLimit = 10,
                ),
                MembershipEntity(
                    name = "비즈니스 프리미엄",
                    description = "기업 고객을 위한 고급 기능을 제공하는 멤버십",
                    type = MembershipType.B2B,
                    price = 150000L,
                    durationDays = 180,
                    conversationLimit = 100,
                    analysisLimit = 20,
                )
            )
            
            membershipRepository.saveAll(memberships)
        }
    }

    private fun initializeUsers() {
        if (userRepository.count() == 0L) {
            val users = listOf(
                UserEntity(
                    name = "김철수",
                    email = "kim.chulsoo@example.com",
                ),
                UserEntity(
                    name = "이영희",
                    email = "lee.younghee@example.com",
                ),
                UserEntity(
                    name = "박민수",
                    email = "park.minsu@example.com",
                ),
                UserEntity(
                    name = "정수진",
                    email = "jung.sujin@example.com",
                ),
                UserEntity(
                    name = "최동현",
                    email = "choi.donghyun@example.com",
                ),
                UserEntity(
                    name = "강지영",
                    email = "kang.jiyoung@example.com",
                ),
                UserEntity(
                    name = "윤성호",
                    email = "yoon.sungho@example.com",
                ),
                UserEntity(
                    name = "한미영",
                    email = "han.miyoung@example.com",
                ),
                UserEntity(
                    name = "송태호",
                    email = "song.taeho@example.com",
                ),
                UserEntity(
                    name = "임소연",
                    email = "lim.soyeon@example.com",
                )
            )
            
            userRepository.saveAll(users)
        }
    }
} 
