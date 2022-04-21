package io.may4th.tge22.web.payload;

import io.may4th.tge22.common.Color;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@ApiModel
@Getter
public class ColorWithUUID {

    @ApiModelProperty(required = true)
    private final Color color;

    @ApiModelProperty(required = true)
    private final UUID id;
}
