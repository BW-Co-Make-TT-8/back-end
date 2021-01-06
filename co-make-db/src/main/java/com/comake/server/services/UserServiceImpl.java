package com.comake.server.services;
import com.comake.server.exceptions.ResourceFoundException;
import com.comake.server.exceptions.ResourceNotFoundException;
import com.comake.server.models.Post;
import com.comake.server.models.Role;
import com.comake.server.models.User;
import com.comake.server.models.UserRoles;
import com.comake.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleService roleService;

    public User findUserById(long id) throws
            ResourceNotFoundException
    {
        return userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username)
    {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();

        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        if (user.getUserPosts().size() > 0)
        {
            throw new ResourceFoundException("Don't update UserPosts through posts!");
        }

        User newUser = new User();

//        if (user.getPosts().size() > 0)
//        {
//            throw new ResourceFoundException("Posts are not updated through users!");
//        }

        if (user.getUserid() != 0)
        {
            newUser = userrepos.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
        }

        newUser.setUsername(user.getUsername()
                .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setEmail(user.getEmail()
                .toLowerCase());

        newUser.getRoles()
                .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                    .getRoleid());
            newUser.getRoles()
                    .add(new UserRoles(newUser,
                            addRole));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(
            User user,
            long id)
    {
        User currentUser = findUserById(id);

        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername()
                    .toLowerCase());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPassword(user.getPassword());
        }

        if (user.getEmail() != null)
        {
            currentUser.setEmail(user.getEmail()
                    .toLowerCase());
        }

        if (user.getComments() != null)
        {
            currentUser.setComments(user.getComments());
        }

        if (user.getRoles()
                .size() > 0)
        {
            currentUser.getRoles()
                    .clear();
            for (UserRoles ur : user.getRoles())
            {
                Role addRole = roleService.findRoleById(ur.getRole()
                        .getRoleid());

                currentUser.getRoles()
                        .add(new UserRoles(currentUser,
                                addRole));
            }
        }
        return userrepos.save(currentUser);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }

//    @Override
//    public List<Post> getPosts(long userid)
//    {
//        List<Post> usersPosts = new ArrayList<>();
//        User user = new User();
//        user = userrepos.findById(userid).orElseThrow(() -> new EntityNotFoundException("User with id " + userid + " not found."));
//
//        return user.get;
//    }
}
