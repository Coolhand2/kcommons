package org.example.commons.entities.filters;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.commons.api.Filter;
import org.example.commons.entities.User;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserFilter implements Filter<User> {

    public static final UserFilter DEFAULT = new UserFilter();

    @Getter
    @Setter
    @Builder.Default
    private String id = "";

    @Getter
    @Setter
    @Builder.Default
    private String username = "";

    @Getter
    @Setter
    @Builder.Default
    private String email = "";

    @Getter
    @Setter
    @Builder.Default
    private String pkiDn = "";

    @Getter
    @Setter
    @Builder.Default
    private String phoneNumber = "";

    @Getter
    @Setter
    @Builder.Default
    private String organization = "";

    @Getter
    @Setter
    @Builder.Default
    private String role = "";

}
