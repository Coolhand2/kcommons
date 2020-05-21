package org.example.commons.api

import javax.persistence.metamodel.SingularAttribute

interface Repository<T, S> {
    fun findAll(): MutableList<T>
    fun findById(id: S): T
    fun findByIds(vararg ids: S): MutableList<T>
    fun findByIds(ids: Iterable<S>): MutableList<T>
    fun <X> findByColumn(column: SingularAttribute<T, X>, vararg values: X): MutableList<T>
    fun <X> findByColumn(column: SingularAttribute<T, X>, values: Iterable<X>): MutableList<T>
    fun create(vararg entities: T)
    fun create(entities: Iterable<T>)
    fun update(vararg entities: T)
    fun update(entities: Iterable<T>)
    fun delete(vararg entities: T)
    fun delete(entities: Iterable<T>)
}