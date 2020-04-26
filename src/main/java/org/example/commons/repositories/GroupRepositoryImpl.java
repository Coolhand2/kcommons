package org.example.commons.repositories;

import org.example.commons.AbstractRepository;
import org.example.commons.api.Filter;
import org.example.commons.api.Filterable;
import org.example.commons.entities.Group;
import org.example.commons.entities.Group_;
import org.example.commons.entities.filters.GroupFilter;
import org.example.commons.repositories.api.GroupRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class GroupRepositoryImpl extends AbstractRepository<Group, Long> implements GroupRepository {
    protected GroupRepositoryImpl() {
        super(Group.class);
    }

    @Override
    public List<Group> filter(Filter<Group> f) {
        GroupFilter filter = (GroupFilter) f;
        if(GroupFilter.DEFAULT.equals(filter)) {
            return findAll();
        }

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Group> query = builder.createQuery(Group.class);
        Root<Group> root = query.from(Group.class);
        List<Predicate> predicates = new ArrayList<>();

        if(!filter.getId().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(Group_.id).as(String.class)), '%' + filter.getId().toLowerCase() + '%'));
        }
        if(!filter.getName().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(Group_.name)), '%' + filter.getName().toLowerCase() + '%'));
        }
        if(!filter.getDescription().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(Group_.description)), '%' + filter.getDescription().toLowerCase() + '%'));
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
