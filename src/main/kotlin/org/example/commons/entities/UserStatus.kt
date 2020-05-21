package org.example.commons.entities

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Transient

data class UserStatus(
        @Id
        @GeneratedValue
        val id: Long = 0L,

        var name: String = "",

        @Transient
        var editing: Boolean = false
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
    }
}