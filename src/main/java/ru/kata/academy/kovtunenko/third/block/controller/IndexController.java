package ru.kata.academy.kovtunenko.third.block.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping(value = {"/index", "/"})
    public String showIndex(ModelMap model, Principal principal) {
        if (principal != null) {
            model.addAttribute("userName", principal.getName());
        } else {
            model.addAttribute("userName", "anonymous");
        }

        return "index";
    }
}
