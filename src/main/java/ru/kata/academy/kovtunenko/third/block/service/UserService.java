package ru.kata.academy.kovtunenko.third.block.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.academy.kovtunenko.third.block.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> get();
    User getById(Long id);
    void save(User user);
    void update(User user);
    void deleteById(Long id);
}
