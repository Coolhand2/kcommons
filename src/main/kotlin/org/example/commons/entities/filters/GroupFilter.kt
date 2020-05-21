package org.example.commons.entities.filters

import org.example.commons.api.Filter
import org.example.commons.entities.Group

data class GroupFilter(
        var id: String = "",
        var name: String = "",
        var description: String = ""
) : Filter<Group> {
    companion object {
        val default: GroupFilter = GroupFilter()
    }
}