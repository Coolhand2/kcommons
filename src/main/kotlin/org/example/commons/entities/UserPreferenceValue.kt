package org.example.commons.entities

import java.io.Serializable
import javax.persistence.Id
import javax.persistence.ManyToOne

data class UserPreferenceValue(
        @Id
        @ManyToOne
        val user: User = User(),

        @Id
        @ManyToOne
        val preferenceValue: PreferenceValue = PreferenceValue()
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
    }
}