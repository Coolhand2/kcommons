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
public class PhoneNumber extends AbstractEntity<PhoneNumber> {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumber.class);

    public static final PhoneNumber DEFAULT = new PhoneNumber();

    @Getter
    @Setter
    @Builder.Default
    private String areaCode = "";

    @Getter
    @Setter
    @Builder.Default
    private String frontThree = "";

    @Getter
    @Setter
    @Builder.Default
    private String backFour = "";

    @Override
    public PhoneNumber clone() {
        try {
            return (PhoneNumber) super.clone();
        } catch (CloneNotSupportedException e) {
            LOG.error("Could not clone PhoneNumber object {}", this);
            return new PhoneNumber();
        }
    }

    @Override
    public int compareTo(PhoneNumber that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }


    @Override
    protected boolean isEqualTo(PhoneNumber that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    protected int getHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
