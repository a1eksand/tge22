package io.may4th.tge22.domain.api.entities;

import io.may4th.tge22.common.CommonValidation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

@Accessors(chain = true)
@Entity
@Getter
@Setter
public class Message {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    private UUID roomId;

    @NotNull
    private Instant instant = ZonedDateTime.now().toInstant();

    @NotNull
    @NotBlank
    @Size(min = 1, max = 4096)
    @Pattern(regexp = CommonValidation.TRIMMED_MULTI_LINE)
    private String body;
}
