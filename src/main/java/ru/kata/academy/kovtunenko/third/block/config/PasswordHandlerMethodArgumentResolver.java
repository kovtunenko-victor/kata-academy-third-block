package ru.kata.academy.kovtunenko.third.block.config;

import org.springframework.core.MethodParameter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.kata.academy.kovtunenko.third.block.model.User;

public class PasswordHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final PasswordEncoder passwordEncoder;

    public PasswordHandlerMethodArgumentResolver(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(EncodePassword.class) != null ;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (webRequest.getParameter("password") != null && webRequest.getParameter("password").length() > 0) {
            return passwordEncoder.encode(webRequest.getParameter("password"));
        }
        return "";
    }
}
