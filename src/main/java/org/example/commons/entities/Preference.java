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
public class Preference extends AbstractEntity<Preference> {

    private static final Logger LOG = LoggerFactory.getLogger(Preference.class);

    public static final Preference DEFAULT = new Preference();

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
    public Preference clone() {
        try {
            return (Preference) super.clone();
        } catch (CloneNotSupportedException e) {
            LOG.error("Could not clone Preference object {}", this);
            return new Preference();
        }
    }

    @Override
    public int compareTo(Preference that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }


    @Override
    protected boolean isEqualTo(Preference that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    protected int getHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
