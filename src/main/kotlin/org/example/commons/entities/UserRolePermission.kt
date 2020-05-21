package org.example.commons.entities

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Transient

data class UserRolePermission(
        @OneToOne
        val role: UserRole,

        @OneToMany
        var permissions: List<UserPermission> = listOf(),

        var grantedStart: LocalDateTime? = null,

        var grantedEnd: LocalDateTime? = null,

        @Transient
        var editing: Boolean = false
) : Serializable {
    fun grantPermanently() {
        grantedStart = null
        grantedEnd = null
    }
    fun grantTemporarily(start: LocalDateTime, end: LocalDateTime) {
        grantedStart = start
        grantedEnd = end
    }
    companion object {
        const val serialVersionUID: Long = 1L
    }
}