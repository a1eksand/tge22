package io.may4th.tge22.security.impl;

import io.may4th.tge22.security.api.Secured;
import io.may4th.tge22.security.api.SecurityHandlerInterceptor;
import io.may4th.tge22.security.api.exceptions.AccessDeniedException;
import io.may4th.tge22.security.api.exceptions.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

@Component
public class SecurityHandlerInterceptorImpl implements SecurityHandlerInterceptor {

    private final UserDetailsRequestHolder userDetailsRequestHolder;

    @Autowired
    public SecurityHandlerInterceptorImpl(UserDetailsRequestHolder userDetailsRequestHolder) {
        this.userDetailsRequestHolder = userDetailsRequestHolder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        var handlerMethod = (HandlerMethod) handler;

        var classLevelSecure = handlerMethod.getBeanType().getAnnotation(Secured.class);
        if (Objects.nonNull(classLevelSecure)) {
            check(classLevelSecure);
        }

        var methodLevelSecure = handlerMethod.getMethodAnnotation(Secured.class);
        if (Objects.nonNull(methodLevelSecure)) {
            check(methodLevelSecure);
        }

        return true;
    }

    private void check(Secured secured) {
        var userDetails = userDetailsRequestHolder.getUserDetails();
        if (secured.value().length == 0 && userDetails.isPresent()) {
            return;
        }

        var requiredAuthorities = Arrays.asList(secured.value());
        if (userDetails
            .orElseThrow(AuthenticationException::new)
            .getAuthorities()
            .stream()
            .noneMatch(grantedAuthority -> requiredAuthorities.contains(grantedAuthority.getAuthority()))
        ) {
            throw new AccessDeniedException();
        }
    }
}
