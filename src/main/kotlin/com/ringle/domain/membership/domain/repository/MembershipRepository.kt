package com.ringle.domain.membership.domain.repository

import com.ringle.domain.membership.domain.entity.MembershipEntity
import com.ringle.domain.membership.domain.enums.MembershipType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MembershipRepository : JpaRepository<MembershipEntity, Long> {
    fun findByTypeAndIsActiveTrue(type: MembershipType): List<MembershipEntity>
    fun findByName(name: String): MembershipEntity?
    fun findByIsActiveTrue(): List<MembershipEntity>
}
