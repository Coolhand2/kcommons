package org.example.commons.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.commons.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "preferences")
public class PreferenceType extends AbstractEntity<PreferenceType> {

    private static final Logger LOG = LoggerFactory.getLogger(PreferenceType.class);

    public static final PreferenceType DEFAULT = new PreferenceType();

    @Id
    @GeneratedValue
    @Getter
    @Builder.Default
    private Long id = 0L;

    @Getter
    @Setter
    @Builder.Default
    private String name = "";

    @Override
    public PreferenceType clone() {
        try {
            return (PreferenceType) super.clone();
        } catch (CloneNotSupportedException e) {
            LOG.error("Could not clone PreferenceType object {}", this);
            return new PreferenceType();
        }
    }



    @Override
    public int compareTo(PreferenceType that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }

    @Override
    protected boolean isEqualTo(PreferenceType that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    protected int getHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
