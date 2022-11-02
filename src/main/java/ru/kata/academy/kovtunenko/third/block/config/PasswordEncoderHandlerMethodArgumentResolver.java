package ru.kata.academy.kovtunenko.third.block.config;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.MethodParameter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.kata.academy.kovtunenko.third.block.model.User;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class PasswordEncoderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderHandlerMethodArgumentResolver(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(RequestBodyWithEncodedPassword.class) != null ;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ServletRequest servletRequest = webRequest.getNativeRequest(ServletRequest.class);

        if (servletRequest == null) {
            throw new Exception("Can not get ServletRequest from NativeWebRequest");
        }

        return parseRequestBody(servletRequest.getReader());
    }

    private User parseRequestBody(BufferedReader reader) throws IOException {
        String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        try {
            JSONObject jsonObject = (JSONObject) (new JSONParser().parse(body));
            User user = new Gson().fromJson(jsonObject.toJSONString(), User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            return user;
        } catch (ParseException ex) {
            throw new IOException("Cannot parse request body. Maybe body is not JSON?", ex);
        }
    }
}
