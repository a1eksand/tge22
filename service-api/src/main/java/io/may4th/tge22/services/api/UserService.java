package io.may4th.tge22.services.api;

import io.may4th.tge22.services.api.tos.NewUserTO;
import io.may4th.tge22.services.api.tos.UserTO;

public interface UserService extends BaseService<UserTO, NewUserTO> {

    UserTO findByUsername(String username);

    UserTO save(String username, String hash);
}
