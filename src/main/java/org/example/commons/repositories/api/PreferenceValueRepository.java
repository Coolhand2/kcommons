package org.example.commons.repositories.api;

import org.example.commons.api.Repository;
import org.example.commons.entities.Preference;
import org.example.commons.entities.PreferenceType;
import org.example.commons.entities.UserPreference;
import org.example.commons.entities.User;

import java.util.List;

public interface PreferenceValueRepository extends Repository<UserPreference, Long> {
    List<UserPreference> findByUser(User user);
    List<UserPreference> findByPreference(Preference preference);
    List<UserPreference> findByType(PreferenceType type);

}
