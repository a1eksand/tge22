package io.may4th.tge22.services.api.tos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.UUID;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class RoomTO {

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000146")
    private UUID id;

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000147")
    private UUID userId;

    @ApiModelProperty(required = true, example = "NY2020")
    private String title;

    @ApiModelProperty(required = true)
    Set<UUID> users;
}
