package org.example.commons.repositories.api;

import org.example.commons.api.Repository;
import org.example.commons.entities.Group;
import org.example.commons.entities.Membership;
import org.example.commons.entities.MembershipRole;
import org.example.commons.entities.User;

import java.util.List;

public interface MembershipRepository extends Repository<Membership, Membership.MembershipKey> {
    List<Membership> findByUser(User user);
    List<Membership> findByGroup(Group group);
    List<Membership> findByRole(MembershipRole role);
    List<Membership> findByUserAndGroup(User user, Group group);
    List<Membership> findByUserAndGroupAndRole(User user, Group group, MembershipRole role);
}
