package org.example.commons.providers;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProvider {

    @Produces
    private EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("AppDB").createEntityManager();
    }

    private void destroy(@Disposes final EntityManager em){

    }
}
