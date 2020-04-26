package org.example.commons.repositories.api;

import org.example.commons.api.Filterable;
import org.example.commons.api.Repository;
import org.example.commons.entities.User;
import org.example.commons.entities.UserStatus;
import org.example.commons.entities.UserType;

import java.util.List;

public interface UserRepository extends Repository<User, Long>, Filterable<User> {
    List<User> findByType(UserType type);
    List<User> findByStatus(UserStatus status);
    List<User> findByTypeAndStatus(UserType type, UserStatus status);
}
