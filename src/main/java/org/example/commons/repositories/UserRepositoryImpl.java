package org.example.commons.repositories;

import org.example.commons.AbstractRepository;
import org.example.commons.api.Filter;
import org.example.commons.api.Filterable;
import org.example.commons.entities.Address_;
import org.example.commons.entities.PhoneNumber_;
import org.example.commons.entities.User;
import org.example.commons.entities.UserStatus;
import org.example.commons.entities.UserType;
import org.example.commons.entities.User_;
import org.example.commons.entities.filters.UserFilter;
import org.example.commons.repositories.api.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
        if(!filter.getVerificationKey().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(User_.verificationKey)), '%' + filter.getVerificationKey().toLowerCase() + '%'));
        }
        if(!filter.getPhoneNumber().isEmpty()) {
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.areaCode)), '%' + filter.getPhoneNumber().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.frontThree)), '%' + filter.getPhoneNumber().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.phoneNumber).get(PhoneNumber_.backFour)), '%' + filter.getPhoneNumber().toLowerCase() + '%')
            ));
        }
        if(!filter.getAddress().isEmpty()) {
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get(User_.address).get(Address_.city)), '%' + filter.getAddress().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.country)), '%' + filter.getAddress().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.state)), '%' + filter.getAddress().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.street1)), '%' + filter.getAddress().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.street2)), '%' + filter.getAddress().toLowerCase() + '%'),
                    builder.like(builder.lower(root.get(User_.address).get(Address_.zipcode)), '%' + filter.getAddress().toLowerCase() + '%')
            ));
        }
        if(!filter.getType().isEmpty()) {
            filter.getType().forEach(t -> predicates.add(builder.equal(root.get(User_.type), t)));
        }
        if(!filter.getStatus().isEmpty()) {
            filter.getStatus().forEach(s -> predicates.add(builder.equal(root.get(User_.status), s)));
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

    @Override
    public List<User> findByType(UserType type) {
        return findByColumn(User_.type, type);
    }

    @Override
    public List<User> findByStatus(UserStatus status) {
        return findByColumn(User_.status, status);
    }

    @Override
    public List<User> findByTypeAndStatus(UserType type, UserStatus status) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.and(
                builder.equal(root.get(User_.type), type),
                builder.equal(root.get(User_.status), status)
        ));
        return getSession().createQuery(query).getResultList();
    }
}
