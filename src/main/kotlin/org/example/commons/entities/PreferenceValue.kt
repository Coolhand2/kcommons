package org.example.commons.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "preference_values")
data class PreferenceValue(
        @Id
        @GeneratedValue
        val id: Long = 0L,

        @ManyToOne
        val preference: Preference = Preference(),

        val value: String = "",

        @Transient
        val editing: Boolean = false
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
        val default: PreferenceValue = PreferenceValue()
    }
}