package io.may4th.tge22.web.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@ApiModel
@Getter
public class OptionalValue<T> {

    private static final OptionalValue<?> EMPTY = new OptionalValue<>(null);

    public static <T> OptionalValue<T> empty() {
        @SuppressWarnings("unchecked")
        OptionalValue<T> t = (OptionalValue<T>) EMPTY;
        return t;
    }

    @ApiModelProperty
    private final T value;

    @ApiModelProperty(required = true)
    public boolean isPresent() {
        return Objects.nonNull(value);
    }
}
