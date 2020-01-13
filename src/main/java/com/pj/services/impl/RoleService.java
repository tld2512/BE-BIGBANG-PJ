package com.pj.services.impl;

import com.pj.models.user.Role;
import com.pj.repositories.user.RoleRepository;
import com.pj.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void save(Role role) {
roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
roleRepository.deleteById(id);
    }
}
