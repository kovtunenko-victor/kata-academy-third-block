package ru.kata.academy.kovtunenko.third.block.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.academy.kovtunenko.third.block.model.User;
import ru.kata.academy.kovtunenko.third.block.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> get() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(User.getEmptyUser());
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.delete(getById(id));
    }
}