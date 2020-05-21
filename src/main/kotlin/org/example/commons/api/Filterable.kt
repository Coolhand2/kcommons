package org.example.commons.api

interface Filterable<T> {
    fun filter(f: Filter<T>): List<T>
}