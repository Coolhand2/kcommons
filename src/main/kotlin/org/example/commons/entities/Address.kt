package org.example.commons.entities

import javax.persistence.Embeddable
import javax.persistence.Transient

@Embeddable
data class Address(
        val street1: String = "",
        val street2: String = "",
        val city: String = "",
        val state: String = "",
        val country: String = "",
        val zipcode: String = "",
        @Transient
        val editing: Boolean = false
) {
    companion object {
        val default: Address = Address()
    }
}