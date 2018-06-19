package dto;

import org.apache.commons.lang.builder.EqualsBuilder;


public class BaseDTO {

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
