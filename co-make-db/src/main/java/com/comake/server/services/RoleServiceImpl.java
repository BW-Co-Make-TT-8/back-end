package com.comake.server.services;
import com.comake.server.exceptions.ResourceNotFoundException;
import com.comake.server.models.Role;
import com.comake.server.repository.RoleRepository;
import com.comake.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRepository userrepos;

    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Role findRoleById(long id)
    {
        return rolerepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
    }

    @Override
    public Role findByName(String name)
    {
        Role rr = rolerepos.findByNameIgnoreCase(name);

        if (rr != null)
        {
            return rr;
        } else
        {
            throw new ResourceNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public Role save(Role role)
    {
        if (role.getUsers()
                .size() > 0)
        {
            throw new ResourceNotFoundException("User Roles are not updated through Role.");
        }
        return rolerepos.save(role);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        rolerepos.deleteAll();
    }

    @Transactional
    @Override
    public Role update(
            long id,
            Role role)
    {
        if (role.getName() == null)
        {
            throw new ResourceNotFoundException("No role name found to update!");
        }

        if (role.getUsers()
            .size() > 0)
        {
            throw new ResourceNotFoundException("User Roles are not updated through Role.");
        }

        Role newRole = findRoleById(id);

        rolerepos.updateRoleName(userAuditing.getCurrentAuditor()
                    .get(),
                id,
                role.getName());
        return findRoleById(id);
    }
}
