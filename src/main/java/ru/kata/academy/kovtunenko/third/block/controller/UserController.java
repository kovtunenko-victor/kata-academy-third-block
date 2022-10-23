package ru.kata.academy.kovtunenko.third.block.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.academy.kovtunenko.third.block.model.User;
import ru.kata.academy.kovtunenko.third.block.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService service;

    @GetMapping(value = "/users")
    public String printUser(ModelMap model) {
        model.addAttribute("users", service.get());
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", service.getById(id));
        model.addAttribute("method", "PATCH");
        model.addAttribute("btnValue", "Edit User");
        return "edit";
    }

    @GetMapping("/users/create")
    public String editUser(ModelMap model) {
        model.addAttribute("user", User.getEmptyUser());
        model.addAttribute("method", "POST");
        model.addAttribute("btnValue", "Create User");
        return "edit";
    }

    @PostMapping("/users/update")
    public String createUser(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/users";
    }

    @PatchMapping("/users/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        service.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable(value = "id", required = true) Long id) {
        service.deleteById(id);
        return "redirect:/users";
    }
}
