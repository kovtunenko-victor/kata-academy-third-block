package ru.kata.academy.kovtunenko.third.block.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/details")
    public String showUser() {
        return "userApp/mainPage";
    }

    @GetMapping(value = "/admin/users")
    public String printUser() {
        return "userApp/mainPage";
    }
}
