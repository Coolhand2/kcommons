package org.example.commons.repositories.api;

import org.example.commons.api.Repository;
import org.example.commons.entities.Preference;
import org.example.commons.entities.PreferenceType;
import org.example.commons.entities.User;

import java.util.List;

public interface PreferenceRepository extends Repository<Preference, Long> {
    List<Preference> findByName(String name);
    List<Preference> findByType(PreferenceType type);
}
