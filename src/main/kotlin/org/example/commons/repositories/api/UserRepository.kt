package org.example.commons.repositories.api

import org.example.commons.api.Filterable
import org.example.commons.api.Repository
import org.example.commons.entities.User
import org.example.commons.entities.UserRole
import org.example.commons.entities.UserStatus
import org.example.commons.entities.UserType

interface UserRepository : Repository<User, Long> {
    fun findByType(type: UserType): List<User>
    fun findByStatus(status: UserStatus): List<User>
    fun findByRole(role: UserRole): List<User>
    fun findByTypeAndStatus(type: UserType, status: UserStatus): List<User>
    fun findByTypeAndRole(type: UserType, role: UserRole): List<User>
    fun findByStatusAndRole(status: UserStatus, role: UserRole): List<User>
    fun findByTypeAndStatusAndRole(type: UserType, status: UserStatus, role: UserRole): List<User>
}