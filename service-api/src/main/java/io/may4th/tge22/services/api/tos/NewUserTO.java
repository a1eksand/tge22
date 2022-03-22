package io.may4th.tge22.services.api.tos;

import io.may4th.tge22.common.CommonValidation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
public class NewUserTO {

    @NotNull
    private UUID id;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 64)
    @Pattern(regexp = CommonValidation.WITHOUT_WHITESPACE)
    private String username;

    @NotNull
    private String hash;
}
