package ru.kata.academy.kovtunenko.third.block.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.academy.kovtunenko.third.block.model.Role;
import ru.kata.academy.kovtunenko.third.block.service.RoleService;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles/")
public class RoleRestController {
    private final RoleService service;

    public RoleRestController(RoleService service) {
        this.service = service;
    }

    @GetMapping(value = "/get", produces = "application/json; charset=UTF-8")
    public Set<Role.Response> getRoles() {
        return service.get().stream().map(x-> x.new Response()).collect(Collectors.toSet());
    }
}
