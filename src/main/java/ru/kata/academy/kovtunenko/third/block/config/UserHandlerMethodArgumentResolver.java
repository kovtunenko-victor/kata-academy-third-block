package ru.kata.academy.kovtunenko.third.block.config;

import org.springframework.core.MethodParameter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.kata.academy.kovtunenko.third.block.model.Role;
import ru.kata.academy.kovtunenko.third.block.model.User;
import ru.kata.academy.kovtunenko.third.block.service.RoleService;

import java.util.HashSet;
import java.util.Set;

public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserHandlerMethodArgumentResolver(PasswordEncoder passwordEncoder, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(ModelAttributeWithEncodePassword.class) != null ;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User templateUser = new User();
        Set<Role> roles = new HashSet<>();

        if (webRequest.getParameter("id") != null && webRequest.getParameter("id").length() > 0) {
            templateUser.setId(Long.valueOf(webRequest.getParameter("id")));
        }

        if (webRequest.getParameter("login") != null) {
            templateUser.setLogin(webRequest.getParameter("login"));
        }

        if (webRequest.getParameter("name") != null) {
            templateUser.setName(webRequest.getParameter("name"));
        }

        if (webRequest.getParameter("title") != null) {
            templateUser.setTitle(webRequest.getParameter("title"));
        }

        if (webRequest.getParameterMap().get("roles") != null) {
            for(String role : webRequest.getParameterMap().get("roles")) {
                roles.add(roleService.getById(Long.valueOf(role)));
            }
            templateUser.setRoles(roles);
        }

        if (webRequest.getParameter("password") != null && webRequest.getParameter("password").length() > 0) {
            templateUser.setPassword(passwordEncoder.encode(webRequest.getParameter("password")));
        } else {
            templateUser.setPassword("");
        }

        return templateUser;
    }
}
