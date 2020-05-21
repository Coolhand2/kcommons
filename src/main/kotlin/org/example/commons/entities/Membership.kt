package org.example.commons.entities

import java.io.Serializable
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

        @ManyToOne
        private val role: MembershipRole = MembershipRole(),

        @Transient
        private val editing: Boolean = false
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
        val default: Membership = Membership()
    }
}