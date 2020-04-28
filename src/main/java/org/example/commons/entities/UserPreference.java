package org.example.commons.entities;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.commons.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder(toBuilder=true)
@Data
@Entity
@Table(name = "preference_values")
public class UserPreference extends AbstractEntity<UserPreference> {
    @Builder.Default
    private Long id = 0L;

    @Builder.Default
    private User user = User.DEFAULT.clone();

    @Builder.Default
    private Preference preference = Preference.DISPLAY_NAME;

    @Builder.Default
    private PreferenceType type = PreferenceType.STRING;

    @Builder.Default
    private String value = "";

    @Override
    public UserPreference clone() {
        return this.toBuilder().build();
    }

    @Override
    public int compareTo(UserPreference that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }

    @Override
    protected boolean isEqualTo(UserPreference that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    protected int getHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
