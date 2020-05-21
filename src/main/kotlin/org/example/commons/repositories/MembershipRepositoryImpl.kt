package org.example.commons.repositories

import org.example.commons.AbstractRepository
import org.example.commons.entities.*
import org.example.commons.repositories.api.MembershipRepository

object MembershipRepositoryImpl : AbstractRepository<Membership, MembershipKey>(Membership::class.java), MembershipRepository {
    override fun findByUser(user: User): List<Membership> {
        return findByColumn(Membership_.user, user)
    }

    override fun findByGroup(group: Group): List<Membership> {
        return findByColumn(Membership_.group, group)
    }

    override fun findByRole(role: MembershipRole): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(root.get(Membership_.roles).`in`(role))
        return session.createQuery(query).resultList
    }

    override fun findByRoles(vararg roles: MembershipRole): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(root.get(Membership_.roles).`in`(*roles))
        return session.createQuery(query).resultList
    }

    override fun findByRoles(roles: List<MembershipRole>): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(root.get(Membership_.roles).`in`(roles))
        return session.createQuery(query).resultList
    }

    override fun findByUserAndGroup(user: User, group: Group): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                builder.equal(root.get(Membership_.group), group)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByUserAndRole(user: User, role: MembershipRole): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                root.get(Membership_.roles).`in`(role)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByUserAndRoles(user: User, vararg roles: MembershipRole): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                root.get(Membership_.roles).`in`(*roles)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByUserAndRoles(user: User, roles: List<MembershipRole>): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                root.get(Membership_.roles).`in`(roles)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByGroupAndRole(group: Group, role: MembershipRole): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.group), group),
                root.get(Membership_.roles).`in`(role)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByGroupAndRoles(group: Group, vararg roles: MembershipRole): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.group), group),
                root.get(Membership_.roles).`in`(*roles)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByGroupAndRoles(group: Group, roles: List<MembershipRole>): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.group), group),
                root.get(Membership_.roles).`in`(roles)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByUserAndGroupAndRole(user: User, group: Group, role: MembershipRole): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                builder.equal(root.get(Membership_.group), group),
                root.get(Membership_.roles).`in`(role)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByUserAndGroupAndRoles(user: User, group: Group, vararg roles: MembershipRole): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                builder.equal(root.get(Membership_.group), group),
                root.get(Membership_.roles).`in`(*roles)
        ))
        return session.createQuery(query).resultList
    }

    override fun findByUserAndGroupAndRoles(user: User, group: Group, roles: List<MembershipRole>): List<Membership> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(Membership::class.java)
        val root = query.from(Membership::class.java)
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                builder.equal(root.get(Membership_.group), group),
                root.get(Membership_.roles).`in`(roles)
        ))
        return session.createQuery(query).resultList
    }
}