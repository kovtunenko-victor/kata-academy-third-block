package ru.kata.academy.kovtunenko.third.block.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.academy.kovtunenko.third.block.model.Role;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("FROM Role")
    Set<Role> getAllRoles();
}
