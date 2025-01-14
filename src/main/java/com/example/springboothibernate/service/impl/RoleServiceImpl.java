package com.example.springboothibernate.service.impl;

import com.example.springboothibernate.model.Role;
import com.example.springboothibernate.repository.RoleRepository;
import com.example.springboothibernate.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Set<Role> findByIds(Set<Long> ids) {
        Set<Role> roles = new HashSet<>();
        for (Long id : ids) {
            roles.add(findById(id));
        }
        return roles;
    }
}
