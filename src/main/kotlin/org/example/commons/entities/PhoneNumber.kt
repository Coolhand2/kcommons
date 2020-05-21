package org.example.commons.entities

import javax.persistence.Embeddable
import javax.persistence.Transient

@Embeddable
data class PhoneNumber(
        val areaCode: String = "",
        val frontThree: String = "",
        val backFour: String = "",
        @Transient
        val editing: Boolean = false
) {
   companion object {
        val default = PhoneNumber()
    }
}