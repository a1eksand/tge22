package io.may4th.tge22.web.services;

import io.may4th.tge22.security.api.PasswordEncoder;
import io.may4th.tge22.security.api.TokenProvider;
import io.may4th.tge22.security.api.UserDetailsService;
import io.may4th.tge22.security.api.exceptions.AuthenticationException;
import io.may4th.tge22.services.api.UserService;
import io.may4th.tge22.web.payload.AuthTokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    @Autowired
    private final TokenProvider tokenProvider;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final UserService userService;

    public AuthTokenResponse signup(String username, String password) {
        return authenticate(userService.save(username, passwordEncoder.encode(password)).getUsername(), password);
    }

    public AuthTokenResponse authenticate(String username, String password) {
        var userDetails = userDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            var now = System.currentTimeMillis();
            var token = tokenProvider.generateToken(userDetails, now);
            return new AuthTokenResponse(token);
        }
        throw new AuthenticationException();
    }
}
