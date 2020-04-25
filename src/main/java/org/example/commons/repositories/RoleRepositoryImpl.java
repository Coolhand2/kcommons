package org.example.commons.repositories;

import java.util.List;
import org.example.commons.AbstractRepository;
import org.example.commons.api.Filter;
import org.example.commons.entities.Role;
import org.example.commons.repositories.api.RoleRepository;

public class RoleRepositoryImpl extends AbstractRepository<Role, Long> implements RoleRepository {
    public RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public List<Role> filter(Filter<Role> filter) {
        return null;
    }
}
