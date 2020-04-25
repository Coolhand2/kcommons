package org.example.commons.repositories;

import java.util.List;
import org.example.commons.AbstractRepository;
import org.example.commons.api.Filter;
import org.example.commons.entities.Organization;
import org.example.commons.repositories.api.OrganizationRepository;

public class OrganizationRepositoryImpl extends AbstractRepository<Organization, Long> implements OrganizationRepository {
    protected OrganizationRepositoryImpl() {
        super(Organization.class);
    }

    @Override
    public List<Organization> filter(Filter<Organization> filter) {
        return null;
    }
}
