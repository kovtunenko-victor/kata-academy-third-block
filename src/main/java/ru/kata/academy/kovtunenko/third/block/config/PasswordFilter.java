package ru.kata.academy.kovtunenko.third.block.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PasswordFilter extends GenericFilterBean {
    private final PasswordEncoder passwordEncoder;

    public PasswordFilter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String uri = httpRequest.getRequestURI();
            String method = httpRequest.getMethod();

            if(uri.contains("/admin/users/update/") && (method.equals("POST") || method.equals("PATCH"))) {
                AddableHttpRequest addableRequest = new AddableHttpRequest(httpRequest);
                addableRequest.addParameter("password", passwordEncoder.encode(request.getParameter("password")));
                chain.doFilter(addableRequest, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}