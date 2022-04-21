package io.may4th.tge22.domain.api.entities;

import io.may4th.tge22.common.Color;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
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
    private Instant instant = ZonedDateTime.now().toInstant();

    @NotNull
    @Size(max = 2)
    @ElementCollection
    private Map<Color, UUID> users = new HashMap<>();

    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    @Pattern(regexp = CommonValidation.TRIMMED_SINGLE_LINE)
    private String title;
}
