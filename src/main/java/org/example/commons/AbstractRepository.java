package org.example.commons;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.example.commons.api.Filter;
import org.example.commons.api.Repository;
import org.hibernate.Session;

abstract public class AbstractRepository<T, S> implements Repository<T, S> {

    @Inject
    private EntityManager em;

    private Class<T> cls;

    protected AbstractRepository(Class<T> clazz) {
        cls = clazz;
    }

    protected Session getSession() {
        return em.unwrap(Session.class);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(cls);
        Root<T> root = query.from(cls);
        query.select(root);
        return getSession().createQuery(query).getResultList();
    }

    @Override
    public T findById(S id) {
        return getSession().find(cls, id);
    }

    @Override
    public List<T> findByIds(S... ids) {
        return findByIds(Arrays.asList(ids));
    }

    @Override
    public List<T> findByIds(List<S> ids) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(cls);
        Root<T> root = query.from(cls);
        query.where(root.get("id").in(ids));
        return getSession().createQuery(query).getResultList();
    }

    @Override
    public <X> List<T> findByColumn(SingularAttribute<T, X> column, X... values) {
        return findByColumn(column, Arrays.asList(values));
    }

    @Override
    public <X> List<T> findByColumn(SingularAttribute<T, X> column, List<X> values) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(cls);
        Root<T> root = query.from(cls);
        query.where(root.get(column).in(values));
        return getSession().createQuery(query).getResultList();
    }

    @Override
    public void create(T... entities) {
        create(Arrays.asList(entities));
    }

    @Override
    public void create(List<T> entities) {
        em.getTransaction().begin();
        entities.forEach(e -> em.persist(e));
        em.getTransaction().commit();
    }

    @Override
    public void update(T... entities) {
        update(Arrays.asList(entities));
    }

    @Override
    public void update(List<T> entities) {
        em.getTransaction().begin();
        entities.forEach(e -> em.merge(e));
        em.getTransaction().commit();
    }

    @Override
    public void delete(T... entities) {
        delete(Arrays.asList(entities));
    }

    @Override
    public void delete(List<T> entities) {
        getSession().getTransaction().begin();
        entities.forEach(e -> getSession().delete(e));
        getSession().getTransaction().commit();
    }

    public abstract List<T> filter(Filter<T> filter);
}
