package ru.kata.academy.kovtunenko.third.block.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AddableHttpRequest extends HttpServletRequestWrapper {
    private final Map<String, String[]> mutableParams = new HashMap<>();

    public AddableHttpRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String[] value = getParameterMap().get(name);

        if (value != null && value.length > 0) {
            return value[0];
        } else {
            return super.getParameter(name);
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> allParameters = new HashMap<>();

        allParameters.putAll(super.getParameterMap());
        allParameters.putAll(mutableParams);

        return Collections.unmodifiableMap(allParameters);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(final String name) {
        return getParameterMap().get(name);
    }

    public void addParameter(String name, String value) {
        if (value != null) {
            mutableParams.put(name, new String[]{value});
        }
    }

}