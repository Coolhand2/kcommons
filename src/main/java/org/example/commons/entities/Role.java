package org.example.commons.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.commons.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Builder
@Data
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity<Role> {

    private static final Logger LOG = LoggerFactory.getLogger(Role.class);

    public static final long serialVersionUID = 1L;

    public static final Role DEFAULT = Role.builder().build();

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = 0L;

    @Builder.Default
    private String name = "";

    @Override
    public Role clone() {
        try {
            return (Role) super.clone();
        } catch (CloneNotSupportedException e) {
            LOG.error("Could not clone Role object {}", this);
            return Role.builder().build();
        }
    }

    @Override
    public int compareTo(Role that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }

    @Override
    protected boolean isEqualTo(Role that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    protected int getHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
