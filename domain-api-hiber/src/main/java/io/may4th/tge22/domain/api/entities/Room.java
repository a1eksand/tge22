package io.may4th.tge22.domain.api.entities;

import io.may4th.tge22.common.CommonValidation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Accessors(chain = true)
@Entity
@Getter
@Setter
public class Room {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    @Pattern(regexp = CommonValidation.TRIMMED_SINGLE_LINE)
    private String title;

    @NotNull
    @Size(max = 2)
    @ElementCollection(targetClass=UUID.class)
    private Set<UUID> users = new HashSet<>();
}
