package org.example.commons.providers

import javax.enterprise.inject.Disposes
import javax.enterprise.inject.Produces
import javax.persistence.EntityManager
import javax.persistence.Persistence

object EntityManagerProvider {
    @get:Produces
    private val entityManager: EntityManager
        get() = Persistence.createEntityManagerFactory("AppDB").createEntityManager()

    private fun destroy(@Disposes em: EntityManager) {}
}