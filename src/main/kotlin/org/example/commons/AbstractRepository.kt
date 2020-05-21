package org.example.commons

import org.example.commons.api.Repository
import org.hibernate.Session
import java.util.*
import java.util.function.Consumer
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.metamodel.SingularAttribute

abstract class AbstractRepository<T, S> protected constructor(private val cls: Class<T>) : Repository<T, S> {
    @Inject
    open lateinit var em: EntityManager

    protected val session: Session
        get() = em.unwrap(Session::class.java)

    override fun findAll(): MutableList<T> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(cls)
        val root = query.from(cls)
        query.select(root)
        return session.createQuery(query).resultList
    }

    override fun findById(id: S): T {
        return session.find(cls, id)
    }

    override fun findByIds(vararg ids: S): MutableList<T> {
        return findByIds(listOf(*ids))
    }

    override fun findByIds(ids: Iterable<S>): MutableList<T> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(cls)
        val root = query.from(cls)
        query.where(root.get<Any>("id").`in`(ids))
        return session.createQuery(query).resultList
    }

    override fun <X> findByColumn(column: SingularAttribute<T, X>, vararg values: X): MutableList<T> {
        return findByColumn(column, listOf(*values))
    }

    override fun <X> findByColumn(column: SingularAttribute<T, X>, values: Iterable<X>): MutableList<T> {
        val builder = session.criteriaBuilder
        val query = builder.createQuery(cls)
        val root = query.from(cls)
        query.where(root[column].`in`(values))
        return session.createQuery(query).resultList
    }

    override fun create(vararg entities: T) {
        create(listOf(*entities))
    }

    override fun create(entities: Iterable<T>) {
        em.transaction.begin()
        entities.forEach { e: T -> em.persist(e) }
        em.transaction.commit()
    }

    override fun update(vararg entities: T) {
        update(listOf(*entities))
    }

    override fun update(entities: Iterable<T>) {
        em.transaction.begin()
        entities.forEach { e -> em.merge(e) }
        em.transaction.commit()
    }

    override fun delete(vararg entities: T) {
        delete(listOf(*entities))
    }

    override fun delete(entities: Iterable<T>) {
        em.transaction.begin()
        entities.forEach { e -> em.remove(e) }
        em.transaction.commit()
    }

}