package org.example.commons.api;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface Repository<T, S> {
    List<T> findAll();
    T findById(S id);
    List<T> findByIds(S... ids);
    List<T> findByIds(List<S> ids);
    <X> List<T> findByColumn(SingularAttribute<T, X> column, X... values);
    <X> List<T> findByColumn(SingularAttribute<T, X> column, List<X> values);
    List<T> filter(Filter<T> filter);
    void create(T... entities);
    void create(List<T> entities);
    void update(T... entities);
    void update(List<T> entities);
    void delete(T... entities);
    void delete(List<T> entities);
}
