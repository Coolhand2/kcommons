package org.example.commons.entities.filters;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GroupFilter {
    public static final GroupFilter DEFAULT = GroupFilter.builder().build();

    @Builder.Default
    private String id = "";

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String description = "";
}
