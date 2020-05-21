package org.example.commons.entities

import java.io.Serializable
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

        var role: UserRole = UserRole(),

        //Lists the permission level needed to edit a user.
        var editPermission: UserPermission = UserPermission(),

        @Transient
        var editing: Boolean = false
) : Serializable {
    companion object {
        const val serialVersionUID = 1L
        val default: User = User()
    }
}