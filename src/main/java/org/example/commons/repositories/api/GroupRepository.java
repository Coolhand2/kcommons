package org.example.commons.repositories.api;

import org.example.commons.api.Filterable;
import org.example.commons.api.Repository;
import org.example.commons.entities.Group;

public interface GroupRepository extends Repository<Group, Long>, Filterable<Group> {
}
