package org.example.commons.repositories;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.example.commons.AbstractRepository;
import org.example.commons.api.Filter;
import org.example.commons.entities.PhoneNumber_;
import org.example.commons.entities.User;
import org.example.commons.entities.User_;
import org.example.commons.entities.filters.UserFilter;
import org.example.commons.repositories.api.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepositoryImpl extends AbstractRepository<User, Long> implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    protected UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> filter(final Filter<User> f) {
        UserFilter filter = (UserFilter) f;
        if(UserFilter.DEFAULT.equals(filter)) {
            return findAll();
        }

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        if(!filter.getId().isEmpty()) {
            predicates.add(builder.like(root.get(User_.id).as(String.class), '%' + filter.getId() + '%'));
        }
        if(!filter.getUsername().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(User_.username)), '%' + filter.getUsername().toLowerCase() + '%'));
        }
        if(!filter.getEmail().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(User_.email)), '%' + filter.getEmail().toLowerCase() + '%'));
        }
        if(!filter.getPkiDn().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(User_.pkiDn)), '%' + filter.getPkiDn().toLowerCase() + '%'));
        }
        if(!filter.getPhoneNumber().isEmpty()) {
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.areaCode)), '%' + filter.getPhoneNumber().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.frontThree)), '%' + filter.getPhoneNumber().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.backFour)), '%' + filter.getPhoneNumber().toLowerCase() + '%')
            ));
        }
        query.select(root);
        if(predicates.size() == 1) {
            query.where(predicates.get(0));
        } else {
            query.where(builder.and(
                    predicates.toArray(new Predicate[0])
            ));
        }
        return getSession().createQuery(query).getResultList();
    }
}
