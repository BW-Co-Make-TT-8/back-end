package com.comake.server.services;

import com.comake.server.models.Role;
import com.comake.server.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role)
    {
        Role newRole = roleRepository.save(role);
        return newRole;
    }
}
