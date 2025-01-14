package com.example.springboothibernate.service;

import com.example.springboothibernate.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();

    Role findById(Long id);

    Set<Role> findByIds(Set<Long> ids);
}
