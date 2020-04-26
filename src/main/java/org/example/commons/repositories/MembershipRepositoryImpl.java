package org.example.commons.repositories;

import org.example.commons.AbstractRepository;
import org.example.commons.entities.Group;
import org.example.commons.entities.Membership;
import org.example.commons.entities.MembershipRole;
import org.example.commons.entities.Membership_;
import org.example.commons.entities.User;
import org.example.commons.repositories.api.MembershipRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MembershipRepositoryImpl extends AbstractRepository<Membership, Membership.MembershipKey> implements MembershipRepository {
    protected MembershipRepositoryImpl() {
        super(Membership.class);
    }

    @Override
    public List<Membership> findByUser(User user) {
        return findByColumn(Membership_.user, user);
    }

    @Override
    public List<Membership> findByGroup(Group group) {
        return findByColumn(Membership_.group, group);
    }

    @Override
    public List<Membership> findByRole(MembershipRole role) {
        return findByColumn(Membership_.type, role);
    }

    @Override
    public List<Membership> findByUserAndGroup(User user, Group group) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Membership> query = builder.createQuery(Membership.class);
        Root<Membership> root = query.from(Membership.class);
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                builder.equal(root.get(Membership_.group), group)
        ));
        return getSession().createQuery(query).getResultList();
    }

    @Override
    public List<Membership> findByUserAndGroupAndRole(User user, Group group, MembershipRole role) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Membership> query = builder.createQuery(Membership.class);
        Root<Membership> root = query.from(Membership.class);
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                builder.equal(root.get(Membership_.group), group),
                builder.equal(root.get(Membership_.type), role)
        ));
        return getSession().createQuery(query).getResultList();
    }
}
