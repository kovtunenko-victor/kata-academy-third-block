package ru.kata.academy.kovtunenko.third.block.service;

import org.springframework.stereotype.Service;
import ru.kata.academy.kovtunenko.third.block.model.Role;
import ru.kata.academy.kovtunenko.third.block.repository.RoleRepository;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> get() {
        return roleRepository.getAllRoles();
    }
}
