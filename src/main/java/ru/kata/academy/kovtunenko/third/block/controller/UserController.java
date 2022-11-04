package ru.kata.academy.kovtunenko.third.block.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.academy.kovtunenko.third.block.model.User;
import ru.kata.academy.kovtunenko.third.block.service.RoleService;
import ru.kata.academy.kovtunenko.third.block.service.UserService;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService service, RoleService roleService) {
        this.userService = service;
        this.roleService = roleService;
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

    @DeleteMapping(value = "/admin/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
