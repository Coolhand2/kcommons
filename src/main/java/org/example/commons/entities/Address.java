package org.example.commons.entities;

import javax.persistence.Embeddable;
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
@Embeddable
public class Address extends AbstractEntity<Address> {

    private static final Logger LOG = LoggerFactory.getLogger(Address.class);

    public static final Address DEFAULT = new Address();

    @Getter
    @Setter
    private String street1;

    @Getter
    @Setter
    private String street2;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String state;

    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private String zipcode;

    @Override
    public Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            LOG.error("Could not clone Address object {}", this);
            return new Address();
        }
    }

    @Override
    public int compareTo(Address that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }


    @Override
    protected boolean isEqualTo(Address that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    protected int getHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
