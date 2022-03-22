package io.may4th.tge22.security.impl;

import io.may4th.tge22.security.api.UserDetails;
import io.may4th.tge22.security.api.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class AuthenticationProvider {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProviderImpl tokenProvider;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationProvider(TokenProviderImpl tokenProvider, UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    Optional<UserDetails> extractUserDetails(HttpServletRequest request) {
        return extractToken(request)
            .filter(StringUtils::hasText)
            .map(tokenProvider::extractAuthTokenTO)
            .filter(tokenProvider::isSingValid)
            .filter(this::isNotExpired)
            .map(authToken -> userDetailsService.loadUserById(authToken.getUserId()));
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        return Optional
            .ofNullable(request)
            .map(req -> req.getHeader(AUTHORIZATION_HEADER));
    }

    private boolean isNotExpired(AuthTokenTO authTokenTo) {
        return System.currentTimeMillis() < authTokenTo.getExpireAt();
    }
}
