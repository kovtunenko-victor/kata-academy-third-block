package ru.kata.academy.kovtunenko.third.block.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.academy.kovtunenko.third.block.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
