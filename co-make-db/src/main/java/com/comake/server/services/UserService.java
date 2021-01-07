package com.comake.server.services;

import com.comake.server.models.Post;
import com.comake.server.models.User;

import java.util.List;

public interface UserService
{
    List<User> findAll();

    List<User> findByNameContaining(String username);

    User findUserById(long id);

    User findByName(String name);

    void delete(long id);

    User save(User user);

    User update(
            User user,
            long id);

    public void deleteAll();

    User findByUsername(String name);

//    List<Post> getPosts(long userid);
}
