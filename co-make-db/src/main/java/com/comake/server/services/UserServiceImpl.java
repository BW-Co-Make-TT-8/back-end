package com.comake.server.services;


import com.comake.server.models.User;
import com.comake.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user)
    {
        User newUser = userRepository.save(user);
        return newUser;
    }
}
