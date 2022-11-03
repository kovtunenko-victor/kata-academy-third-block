package ru.kata.academy.kovtunenko.third.block.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.academy.kovtunenko.third.block.model.User;
import ru.kata.academy.kovtunenko.third.block.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService service) {
        this.userService = service;
    }

    @GetMapping(value = "/get/{userId}", produces = "application/json; charset=UTF-8")
    public User.Response getUserById(@PathVariable("userId") Long id) {
        return userService.getById(id).new Response();
    }

    @GetMapping(value = "/get", produces = "application/json; charset=UTF-8")
    public List<User.Response> getUsers() {
        return userService.get().stream()
                .map(x-> x.new Response())
                .collect(Collectors.toList());
    }

    @PatchMapping(value = "/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
