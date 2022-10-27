package ru.kata.academy.kovtunenko.third.block.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.academy.kovtunenko.third.block.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.login = ?1")
    User findByUsername(String login);
}
