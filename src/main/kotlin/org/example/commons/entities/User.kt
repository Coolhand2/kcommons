package org.example.commons.entities

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        var username: String = "",

        var email: String = "",

        var jwt: String = "",

        var verificationKey: String = "",

        @Embedded
        var phoneNumber: PhoneNumber = PhoneNumber(),

        @Embedded
        var address: Address = Address(),

        @Enumerated
        var type: UserType = UserType(),

        @Enumerated
        var status: UserStatus = UserStatus(),

        @OneToMany
        var permissions: MutableList<UserPermission> = mutableListOf(),

        @ManyToOne
        var permissionRequiredToEdit: UserPermission = UserPermission(),

        @Transient
        var editing: Boolean = false
) : Serializable {

    fun isGranted(permission: UserPermission): Boolean {
        return permission in permissions
    }

    fun addPermission(permission: UserPermission) {
        permissions.add(permission)
    }

    fun addPermissions(permissionList: List<UserPermission>) {
        permissions.addAll(permissionList)
    }

    fun removePermission(permission: UserPermission) {
        TODO("Not yet implemented")
    }

    fun removePermissions(permissions: List<UserPermission>) {
        TODO("Not yet implemented")
    }

    companion object {
        const val serialVersionUID = 1L
        val default: User = User()
    }
}