package io.may4th.tge22.web.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class SignInRequest {

    @ApiModelProperty(required = true, example = "john")
    @NotNull
    @NotBlank
    private String username;

    @ApiModelProperty(required = true, example = "pass")
    @NotNull
    @NotBlank
    private String password;
}
