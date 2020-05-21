package org.example.commons.services.api

import org.example.commons.entities.User
import org.example.commons.entities.UserPermission
import org.example.commons.entities.UserRole

interface UserService {
    fun userHasPermission(user: User, permission: UserPermission): Boolean
    fun grantPermissionsToUsers(users: List<User>, permissions: List<UserPermission>)
    fun grantPermissionToUsers(users: List<User>, permission: UserPermission)
    fun grantPermissionsToUser(user: User, permissions: List<UserPermission>)
    fun grantPermissionToUser(user: User, permission: UserPermission)
    fun removePermissionsFromUsers(users: List<User>, permissions: List<UserPermission>)
    fun removePermissionFromUsers(users: List<User>, permission: UserPermission)
    fun removePermissionsFromUser(user: User, permissions: List<UserPermission>)
    fun removePermissionFromUser(user: User, permission: UserPermission)
    fun grantRoleToUser(user: User, role: UserRole)
    fun removeRoleFromUser(user: User, role: UserRole)
}