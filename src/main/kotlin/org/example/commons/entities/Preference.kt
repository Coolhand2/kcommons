package org.example.commons.entities

import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "preferences")
data class Preference(
        @Id
        @GeneratedValue
        val id: Long = 0L,

        val name: String = "",

        @Enumerated
        val type: PreferenceType = PreferenceType.STRING,

        @ManyToMany
        val options: List<PreferenceValue> = mutableListOf(),

        @ManyToMany
        val defaults: List<PreferenceValue> = mutableListOf(),

        @Transient
        val editing: Boolean = false
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
        val default: Preference = Preference()
    }
}