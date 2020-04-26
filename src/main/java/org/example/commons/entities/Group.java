package org.example.commons.entities;

import javax.persistence.Entity;
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

@Builder(toBuilder=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group extends AbstractEntity<Group> {

    public static final Group DEFAULT = Group.builder().build();

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = 0L;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String description = "";

    @Builder.Default
    @Transient
    private boolean editing = false;

    @Override
    public Group clone() {
        return this.toBuilder().build();
    }

    @Override
    public int compareTo(Group that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }

    @Override
    protected boolean isEqualTo(Group that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    protected int getHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
