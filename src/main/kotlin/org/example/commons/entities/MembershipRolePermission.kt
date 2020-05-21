package org.example.commons.entities

import java.io.Serializable
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Transient

data class MembershipRolePermission(
        @OneToMany
        val role: MembershipRole = MembershipRole(),

        @ManyToOne
        var permissions: List<MembershipPermission> = listOf(),

        @Transient
        var editing: Boolean = false

) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
    }
}