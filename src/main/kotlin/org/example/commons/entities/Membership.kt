package org.example.commons.entities

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "memberships")
@IdClass(MembershipKey::class)
data class Membership(
        @Id
        @ManyToOne
        private val user: User = User(),

        @Id
        @ManyToOne
        private val group: Group = Group(),

        @OneToMany
        private val permissions: List<MembershipPermission> = mutableListOf(),

        @Transient
        private val editing: Boolean = false
) : Serializable {

    fun isGranted(permission: MembershipPermission): Boolean {
        return permission in permissions
    }
    companion object {
        const val serialVersionUID: Long = 1L
        val default: Membership = Membership()
    }
}