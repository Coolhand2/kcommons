package org.example.commons.api;

import java.util.List;

public interface Filterable<T> {
    List<T> filter(Filter<T> f);
}
