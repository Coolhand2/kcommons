package org.example.commons.repositories

import org.example.commons.AbstractRepository
import org.example.commons.api.Filter
import org.example.commons.api.Filterable
import org.example.commons.entities.Group
import org.example.commons.entities.Group_
import org.example.commons.entities.filters.GroupFilter
import org.example.commons.repositories.api.GroupRepository
import java.util.*
import javax.persistence.criteria.Predicate

object GroupRepositoryImpl : AbstractRepository<Group, Long>(Group::class.java), GroupRepository, Filterable<Group> {
    override fun filter(f: Filter<Group>): List<Group> {
        val filter = f as GroupFilter
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Group::class.java)
        val root = query.from(Group::class.java)
        val predicates: MutableList<Predicate> = ArrayList()
        if (filter.id.isNotBlank()) {
            predicates.add(builder.like(builder.lower(root.get(Group_.id).`as`(String::class.java)), '%'.toString() + filter.id.toLowerCase() + '%'))
        }
        if (filter.name.isNotBlank()) {
            predicates.add(builder.like(builder.lower(root.get(Group_.name)), '%'.toString() + filter.name.toLowerCase() + '%'))
        }
        if (filter.description.isNotBlank()) {
            predicates.add(builder.like(builder.lower(root.get(Group_.description)), '%'.toString() + filter.description.toLowerCase() + '%'))
        }
        query.select(root)
        if (predicates.size == 1) {
            query.where(predicates[0])
        } else if (predicates.size > 1){
            query.where(builder.and(
                    *predicates.toTypedArray()
            ))
        }
        return session.createQuery(query).resultList
    }

    override fun findByName(name: String): List<Group> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Group::class.java)
        val root = query.from(Group::class.java)
        query.where(builder.equal(root.get(Group_.name), name))
        return session.createQuery(query).resultList
    }

    override fun findLikeName(name: String): List<Group> {
        val groupFilter = GroupFilter(name = name)
        return filter(groupFilter)
    }

    override fun findLikeDescription(description: String): List<Group> {
        val groupFilter = GroupFilter(description = description)
        return filter(groupFilter)
    }
}