package ru.kata.academy.kovtunenko.third.block.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.academy.kovtunenko.third.block.model.User;
import ru.kata.academy.kovtunenko.third.block.service.UserService;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping("/user/details")
    public String showUser(ModelMap model, Principal principal) {
        model.addAttribute("user", (User)userService.loadUserByUsername(principal.getName()));
        return "userApp/mainPage";
    }

    @GetMapping(value = "/admin/users")
    public String printUser(ModelMap model, Principal principal) {
        model.addAttribute("user", (User)userService.loadUserByUsername(principal.getName()));
        return "userApp/mainPage";
    }
}
