package org.example.commons.entities

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient


@Entity
@Table(name = "common_groups")
data class Group(
        @Id
        @GeneratedValue
        val id: Long = 0L,

        var name: String = "",

        var description: String = "",

        //Lists the UserPermission required to edit a group.
        var userEditPermission: UserPermission = UserPermission(),

        //Lists the local MembershipPermission a member must retain in order to edit this group.
        var groupEditPermission: MembershipPermission = MembershipPermission(),

        @Transient
        var editing: Boolean = false
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 1L
        val default = Group()
    }
}