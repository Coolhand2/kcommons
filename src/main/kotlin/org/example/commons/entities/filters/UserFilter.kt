package org.example.commons.entities.filters

import org.example.commons.api.Filter
import org.example.commons.entities.User
import org.example.commons.entities.UserRole
import org.example.commons.entities.UserStatus
import org.example.commons.entities.UserType

data class UserFilter(
        var id: String = "",
        var username: String = "",
        var email: String = "",
        var phoneNumber: String = "",
        var address: String = "",
        var type: MutableList<UserType> = mutableListOf(),
        var status: MutableList<UserStatus> = mutableListOf(),
        var role: MutableList<UserRole> = mutableListOf()
) : Filter<User> {
    companion object {
        val DEFAULT: UserFilter = UserFilter()
    }
}