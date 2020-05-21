package org.example.commons.repositories

import org.example.commons.AbstractRepository
import org.example.commons.api.Filter
import org.example.commons.api.Filterable
import org.example.commons.entities.*
import org.example.commons.entities.filters.UserFilter
import org.example.commons.repositories.api.UserRepository
import org.slf4j.LoggerFactory
import java.util.*
import java.util.function.Consumer
import javax.persistence.criteria.Predicate

object UserRepositoryImpl : AbstractRepository<User, Long>(User::class.java), UserRepository, Filterable<User> {
    override fun filter(f: Filter<User>): List<User> {
        val filter = f as UserFilter
        if (UserFilter.DEFAULT == filter) {
            return findAll()
        }
        val builder = session.criteriaBuilder
        val query = builder.createQuery(User::class.java)
        val root = query.from(User::class.java)
        val predicates: MutableList<Predicate> = ArrayList()
        if (filter.id.isNotBlank()) {
            predicates.add(builder.like(root.get(User_.id).`as`(String::class.java), '%'.toString() + filter.id + '%'))
        }
        if (filter.username.isNotBlank()) {
            predicates.add(builder.like(builder.lower(root.get(User_.username)), '%'.toString() + filter.username.toLowerCase() + '%'))
        }
        if (filter.email.isNotBlank()) {
            predicates.add(builder.like(builder.lower(root.get(User_.email)), '%'.toString() + filter.email.toLowerCase() + '%'))
        }
        if (filter.phoneNumber.isNotBlank()) {
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.areaCode)), '%'.toString() + filter.phoneNumber.toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.frontThree)), '%'.toString() + filter.phoneNumber.toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.backFour)), '%'.toString() + filter.phoneNumber.toLowerCase() + '%')
            ))
        }
        if (filter.address.isNotBlank()) {
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get(User_.address).get(Address_.city)), '%'.toString() + filter.address.toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.country)), '%'.toString() + filter.address.toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.state)), '%'.toString() + filter.address.toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.street1)), '%'.toString() + filter.address.toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.street2)), '%'.toString() + filter.address.toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.zipcode)), '%'.toString() + filter.address.toLowerCase() + '%')
            ))
        }
        if (filter.type.isNotEmpty()) {
            filter.type.forEach(Consumer { t: UserType? -> predicates.add(builder.equal(root.get(User_.type), t)) })
        }
        if (filter.status.isNotEmpty()) {
            filter.status.forEach(Consumer { s: UserStatus? -> predicates.add(builder.equal(root.get(User_.status), s)) })
        }
        query.select(root)
        if (predicates.size == 1) {
            query.where(predicates[0])
        } else {
            query.where(builder.and(
                    *predicates.toTypedArray()
            ))
        }
        return session.createQuery(query).resultList
    }

    override fun findByType(type: UserType): List<User> {
        return findByColumn(User_.type, type)
    }

    override fun findByStatus(status: UserStatus): List<User> {
        return findByColumn(User_.status, status)
    }

    override fun findByRole(role: UserRole): List<User> {
        return findByColumn(User_.role, role)
    }

    override fun findByTypeAndStatus(type: UserType, status: UserStatus): List<User> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(User::class.java)
        val root = query.from(User::class.java)
        query.where(builder.and(
                builder.equal(root.get(User_.type), type),
                builder.equal(root.get(User_.status), status)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByTypeAndRole(type: UserType, role: UserRole): List<User> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(User::class.java)
        val root = query.from(User::class.java)
        query.where(builder.and(
                builder.equal(root.get(User_.type), type),
                builder.equal(root.get(User_.role), role)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByStatusAndRole(status: UserStatus, role: UserRole): List<User> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(User::class.java)
        val root = query.from(User::class.java)
        query.where(builder.and(
                builder.equal(root.get(User_.status), status),
                builder.equal(root.get(User_.role), role)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByTypeAndStatusAndRole(type: UserType, status: UserStatus, role: UserRole): List<User> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(User::class.java)
        val root = query.from(User::class.java)
        query.where(builder.and(
                builder.equal(root.get(User_.type), type),
                builder.equal(root.get(User_.status), status),
                builder.equal(root.get(User_.role), role)
        ))
        return session.createQuery(query).resultList
    }
}