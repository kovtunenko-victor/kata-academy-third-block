package ru.kata.academy.kovtunenko.third.block.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kata.academy.kovtunenko.third.block.service.RoleService;
import ru.kata.academy.kovtunenko.third.block.service.UserService;

import java.util.List;
import java.util.Set;

@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {
    private final UserService userService;
    private final RoleService roleService;

    public SecurityConfig(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/login", "/error", "/index", "/").permitAll()
                    .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
                    .formLogin()
                .successHandler(successHandler())
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            if (roles.contains("ROLE_ADMIN")) {
                response.sendRedirect("/admin/users");
                return;
            }
            if (roles.contains("ROLE_USER")) {
                response.sendRedirect("/user/details");
                return;
            }
            response.sendRedirect("/index");
        };
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getPasswordHandlerMethodArgumentResolver());
    }

    @Bean
    public UserHandlerMethodArgumentResolver getPasswordHandlerMethodArgumentResolver () {
        return new UserHandlerMethodArgumentResolver(passwordEncoder(), roleService);
    }


}
