package org.example.commons.entities

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

data class Configuration(
        @Id
        @GeneratedValue
        val id: Long = 0L,

        var name: String = "",

        var value: String = "",

        var type: ConfigurationType = ConfigurationType(),

        @ManyToOne
        var permissionRequiredToEdit: UserPermission = UserPermission()
) : Serializable{
    companion object {
        const val serialVersionUID: Long = 1L
    }
}