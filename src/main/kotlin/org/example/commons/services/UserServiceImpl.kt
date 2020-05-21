package org.example.commons.services

import org.example.commons.entities.User
import org.example.commons.entities.UserPermission
import org.example.commons.entities.UserRole
import org.example.commons.repositories.api.UserRepository
import org.example.commons.services.api.UserService
import javax.inject.Inject

object UserServiceImpl : UserService {
    @Inject
    private lateinit var userRepository: UserRepository
    override fun userHasPermission(user: User, permission: UserPermission): Boolean {
        return user.isGranted(permission)
    }

    override fun grantPermissionToUser(user: User, permission: UserPermission) {
        user.addPermission(permission)
        userRepository.update(user)
    }

    override fun grantPermissionsToUser(user: User, permissions: List<UserPermission>) {
        user.addPermissions(permissions)
        userRepository.update(user)
    }

    override fun grantPermissionToUsers(users: List<User>, permission: UserPermission) {
        users.forEach { u -> u.addPermission(permission) }
        userRepository.update(users)
    }

    override fun grantPermissionsToUsers(users: List<User>, permissions: List<UserPermission>) {
        users.forEach { u -> u.addPermissions(permissions) }
        userRepository.update(users)
    }

    override fun removePermissionFromUser(user: User, permission: UserPermission) {
        user.removePermission(permission)
        userRepository.update(user)
    }

    override fun removePermissionsFromUser(user: User, permissions: List<UserPermission>) {
        user.removePermissions(permissions)
        userRepository.update(user)
    }

    override fun removePermissionFromUsers(users: List<User>, permission: UserPermission) {
        users.forEach { u -> u.removePermission(permission) }
        userRepository.update(users)
    }

    override fun removePermissionsFromUsers(users: List<User>, permissions: List<UserPermission>) {
        users.forEach { u -> u.removePermissions(permissions) }
        userRepository.update(users)
    }

    override fun grantRoleToUser(user: User, role: UserRole) {
        grantPermissionsToUser(user, role.permissionsGranted)
    }

    override fun removeRoleFromUser(user: User, role: UserRole) {
        removePermissionsFromUser(user, role.permissionsGranted)
    }
}