package io.may4th.tge22.services.impl.mappers;

import io.may4th.tge22.domain.api.entities.User;
import io.may4th.tge22.services.api.tos.NewUserTO;
import io.may4th.tge22.services.api.tos.UserTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserTO, NewUserTO> {
}
