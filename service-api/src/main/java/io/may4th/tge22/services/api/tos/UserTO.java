package io.may4th.tge22.services.api.tos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
public class UserTO {

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000246")
    private UUID id;

    @ApiModelProperty(required = true, example = "john")
    private String username;

    @JsonIgnore
    private String hash;
}
