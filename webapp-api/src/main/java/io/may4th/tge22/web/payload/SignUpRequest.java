package io.may4th.tge22.web.payload;

import io.may4th.tge22.common.CommonValidation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class SignUpRequest {

    @ApiModelProperty(required = true, example = "john")
    @NotNull
    @NotBlank
    @Size(min = 4, max = 64)
    @Pattern(regexp = CommonValidation.WITHOUT_WHITESPACE)
    private String username;

    @ApiModelProperty(required = true, example = "pass")
    @NotNull
    @NotBlank
    @Size(min = 4, max = 64)
    @Pattern(regexp = CommonValidation.WITHOUT_WHITESPACE)
    private String password;
}
