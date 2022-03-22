package io.may4th.tge22.security.impl;

import io.may4th.tge22.security.api.CurrentUser;
import io.may4th.tge22.security.api.CurrentUserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
public class CurrentUserArgumentResolverImpl implements CurrentUserArgumentResolver {

    private final UserDetailsRequestHolder userDetailsRequestHolder;

    @Autowired
    public CurrentUserArgumentResolverImpl(UserDetailsRequestHolder userDetailsRequestHolder) {
        this.userDetailsRequestHolder = userDetailsRequestHolder;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(CurrentUser.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        var userDetails = userDetailsRequestHolder.getUserDetails().orElse(null);
        return parameter.getParameterType().isInstance(userDetails) ?
            userDetailsRequestHolder.getUserDetails().orElse(null) :
            null;
    }
}
