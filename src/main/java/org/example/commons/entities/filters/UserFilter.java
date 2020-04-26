package org.example.commons.entities.filters;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.example.commons.api.Filter;
import org.example.commons.entities.User;
import org.example.commons.entities.UserStatus;
import org.example.commons.entities.UserType;

@Builder
@Data
public class UserFilter implements Filter<User> {

    public static final UserFilter DEFAULT = UserFilter.builder().build();

    @Builder.Default
    private String id = "";

    @Builder.Default
    private String username = "";

    @Builder.Default
    private String email = "";

    @Builder.Default
    private String pkiDn = "";

    @Builder.Default
    private String verificationKey = "";

    @Builder.Default
    private String phoneNumber = "";

    @Builder.Default
    private String address = "";

    @Builder.Default
    private List<UserType> type = new ArrayList();

    @Builder.Default
    private List<UserStatus> status = new ArrayList();
}