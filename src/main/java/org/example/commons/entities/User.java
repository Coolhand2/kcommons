package org.example.commons.entities;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.commons.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@Entity
@Table(name="users")
public class User extends AbstractEntity<User> {

    public static final long serialVersionUID = 1L;

    public static final User DEFAULT = User.builder().build();

    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = 0L;

    @Builder.Default
    private String username = "";

    @Builder.Default
    private String email = "";

    @Builder.Default
    private String pkiDn = "";

    @Builder.Default
    private String verificationKey = "";

    @Builder.Default
    private PhoneNumber phoneNumber = PhoneNumber.DEFAULT.clone();

    @Builder.Default
    private Address address = Address.DEFAULT.clone();

    @Enumerated
    @Builder.Default
    private UserType type = UserType.MEMBER;

    @Enumerated
    @Builder.Default
    private UserStatus status = UserStatus.UNVERIFIED;

    @Transient
    @Builder.Default
    private boolean editing = false;

    @Override
    public User clone() {
        return this.toBuilder().build();
    }

    @Override
    public int compareTo(User that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }

    @Override
    protected boolean isEqualTo(User that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    protected int getHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
