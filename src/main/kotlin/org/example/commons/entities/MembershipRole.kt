package org.example.commons.entities

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Id
import javax.persistence.Transient

data class MembershipRole(
        @Id
        val id: Long = 0L,

        var name: String = "",

        var grantedStart: LocalDateTime? = null,

        var grantedEnd: LocalDateTime? = null,

        @Transient
        var editing: Boolean = false
) : Serializable {
    fun grantPermanently() {
        grantedStart = null
        grantedEnd = null
    }
    fun grantRestricted(start: LocalDateTime, end: LocalDateTime) {
        grantedStart = start
        grantedEnd = end
    }
    companion object {
        const val serialVersionUID: Long = 1L
    }
}