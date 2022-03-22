package io.may4th.tge22.services.impl;

import io.may4th.tge22.domain.api.UserRepository;
import io.may4th.tge22.domain.api.entities.User;
import io.may4th.tge22.services.api.UserService;
import io.may4th.tge22.services.api.exceptions.ResourceNotFoundException;
import io.may4th.tge22.services.api.tos.NewUserTO;
import io.may4th.tge22.services.api.tos.UserTO;
import io.may4th.tge22.services.impl.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl extends BaseService<User, UserTO, NewUserTO> implements UserService {

    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        super(userMapper, userRepository);
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserTO findByUsername(String username) {
        return userMapper.to(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(userRepository.getClass().getSimpleName(), "username", username)));
    }

    @Override
    @Transactional
    public UserTO save(NewUserTO newUserTO) {
        return saveNew(newUserTO);
    }

    @Override
    @Transactional
    public UserTO save(String username, String hash) {
        return save(new NewUserTO()
            .setId(UUID.randomUUID())
            .setUsername(username)
            .setHash(hash)
        );
    }
}
