package org.example.commons.repositories.api

import org.example.commons.api.Repository
import org.example.commons.entities.*

interface MembershipRepository : Repository<Membership, MembershipKey> {
    fun findByUser(user: User): List<Membership>
    fun findByGroup(group: Group): List<Membership>
    fun findByRole(role: MembershipRole): List<Membership>
    fun findByRoles(vararg roles: MembershipRole): List<Membership>
    fun findByRoles(roles: List<MembershipRole>): List<Membership>
    fun findByUserAndGroup(user: User, group: Group): List<Membership>
    fun findByUserAndRole(user: User, role: MembershipRole): List<Membership>
    fun findByUserAndRoles(user: User, vararg roles: MembershipRole): List<Membership>
    fun findByUserAndRoles(user: User, roles: List<MembershipRole>): List<Membership>
    fun findByGroupAndRole(group: Group, role: MembershipRole): List<Membership>
    fun findByGroupAndRoles(group: Group, vararg roles: MembershipRole): List<Membership>
    fun findByGroupAndRoles(group: Group, roles: List<MembershipRole>): List<Membership>
    fun findByUserAndGroupAndRole(user: User, group: Group, role: MembershipRole): List<Membership>
    fun findByUserAndGroupAndRoles(user: User, group: Group, vararg roles: MembershipRole): List<Membership>
    fun findByUserAndGroupAndRoles(user: User, group: Group, roles: List<MembershipRole>): List<Membership>
}