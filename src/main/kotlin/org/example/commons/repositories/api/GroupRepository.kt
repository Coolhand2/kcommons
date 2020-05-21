package org.example.commons.repositories.api

import org.example.commons.api.Filterable
import org.example.commons.api.Repository
import org.example.commons.entities.Group

interface GroupRepository : Repository<Group, Long> {
    fun findByName(name: String): List<Group>
    fun findLikeName(name: String): List<Group>
    fun findLikeDescription(description: String): List<Group>
}