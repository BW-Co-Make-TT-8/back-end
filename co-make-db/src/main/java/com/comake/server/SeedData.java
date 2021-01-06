package com.comake.server;

import com.comake.server.models.*;
import com.comake.server.repository.*;
import com.comake.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    private CommentService commentService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private PostService postService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        role1 = roleService.save(role1);
        role2 = roleService.save(role2);

        User user1 = new User("sethb", "password", "seth.bradshaw@gmail.com", 90002);
        User user2 = new User("davidc", "password", "david.chang@gmail.com", 90005);
        User user3 = new User("chrisg", "password", "chris.girvin@gmail.com", 90001);

        user1.getRoles().add(new UserRoles(user1, role1));
        user2.getRoles().add(new UserRoles(user2, role1));
        user3.getRoles().add(new UserRoles(user3, role2));

        user1 = userService.save(user1);
        user2 = userService.save(user2);
        user3 = userService.save(user3);

//        Location l1 = new Location(84015);
//        Location l2 = new Location(12345);
//
//        l1 = locationService.save(l1);
//        l2 = locationService.save(l2);

        Post post1 = new Post("imgurl", "Dog Poop On Porch", "Someone put dog poop on my porch!", "1234 main St", "find it on your own.", "Clinton", "Utah", 84015, user1);
        Post post2 = new Post("imgurl", "Pot Hole", "There is a pot hole in front of my building. Unacceptable.", "1234 main St", "find it on your own.", "New York City", "New York", 12345, user2);
        Post post3 = new Post("imgurl", "Dirty Park. Save the Enviornment.", "There is trash littered all over this park.", "1234 main St", "It's hot so bring some water", "Queen Creek", "Arizona", 55555, user1);

        post1 = postService.save(post1);
        post2 = postService.save(post2);
        post3 = postService.save(post3);

        Comment com1 = new Comment("This is my comment on this particular issue: great.", user1, post1);
        Comment com2 = new Comment("This is my comment on this particular issue: this sucks.", user2, post1);
        Comment com3 = new Comment("This is my comment on this particular issue: awesome.", user2, post3);

        com1 = commentService.save(com1);
        com2 = commentService.save(com2);
        com3 = commentService.save(com3);
    }
}
