package dto;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class BaseDTO {

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
