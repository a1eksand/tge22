package io.may4th.tge22.web;

import io.may4th.tge22.security.api.CurrentUserArgumentResolver;
import io.may4th.tge22.security.api.SecurityHandlerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@AllArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private final CurrentUserArgumentResolver currentUserArgumentResolver;
    @Autowired
    private final SecurityHandlerInterceptor securityHandlerInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityHandlerInterceptor);
    }
}
