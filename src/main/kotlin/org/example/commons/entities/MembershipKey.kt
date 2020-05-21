package org.example.commons.entities

import java.io.Serializable

data class MembershipKey(
        val user: Long = 0L,
        val group: Long = 0L
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
    }
}