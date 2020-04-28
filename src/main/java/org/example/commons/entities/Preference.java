package org.example.commons.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.commons.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "preferences")
public class Preference extends AbstractEntity<Preference> {

    public static final Preference DEFAULT = Preference.builder().build();

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = 0L;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private PreferenceType type = PreferenceType.STRING;

    @Builder.Default
    @OneToMany
    private List<PreferenceOption> options = new ArrayList<>();

    @Builder.Default
    @OneToMany
    private List<PreferenceDefault> defaults = new ArrayList<>();

    @Override
    public Preference clone() {
        return this.toBuilder().build();
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
