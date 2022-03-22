package io.may4th.tge22.domain.api.entities;

import io.may4th.tge22.common.CommonValidation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Accessors(chain = true)
@Entity
@Getter
@Setter
@Table(name = "user_")
public class User {

    @Id
    @NotNull
    private UUID id;

    @Column(unique = true)
    @NotNull
    @NotBlank
    @Size(min = 4, max = 64)
    @Pattern(regexp = CommonValidation.WITHOUT_WHITESPACE)
    private String username;

    @NotNull
    private String hash;
}
