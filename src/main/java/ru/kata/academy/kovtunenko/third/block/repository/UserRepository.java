package ru.kata.academy.kovtunenko.third.block.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.academy.kovtunenko.third.block.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.login = ?1")
    @EntityGraph(value = "graph.User.roles")
    User findByUsername(String login);

    @EntityGraph(value = "graph.User.roles")
    List<User> findAll();

    @EntityGraph(value  = "graph.User.roles")
    Optional<User> findById(Long id);
}
