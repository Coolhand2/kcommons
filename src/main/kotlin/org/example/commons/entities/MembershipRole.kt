package org.example.commons.entities

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Transient

data class MembershipRole(
        @Id
        val id: Long = 0L,

        var name: String = "",

        @OneToMany
        var permissionsGranted: MutableList<MembershipPermission> = mutableListOf(),

        @Transient
        var editing: Boolean = false
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
    }
}