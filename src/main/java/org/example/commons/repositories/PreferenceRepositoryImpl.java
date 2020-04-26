package org.example.commons.repositories;

import org.example.commons.AbstractRepository;
import org.example.commons.entities.Preference;
import org.example.commons.entities.PreferenceType;
import org.example.commons.entities.Preference_;
import org.example.commons.entities.User;
import org.example.commons.repositories.api.PreferenceRepository;

import java.util.List;

public class PreferenceRepositoryImpl extends AbstractRepository<Preference, Long> implements PreferenceRepository {
    protected PreferenceRepositoryImpl() {
        super(Preference.class);
    }

    @Override
    public List<Preference> findByName(String name) {
        return findByColumn(Preference_.name, name);
    }

    @Override
    public List<Preference> findByType(PreferenceType type) {
        return findByColumn(Preference_.type, type);
    }
}
