package com.comake.server.controllers;

import com.comake.server.ServerApplication;
import com.comake.server.models.*;
import com.comake.server.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@WithMockUser(username = "admin", roles = {"ADMIN", "DATA"})
class PostControllerTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private UserPostService userPostService;

    List<Post> postsList = new ArrayList<>();

    @Before
    public void setUp() throws
            Exception
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
//        Role role1 = new Role("ADMIN");
//        Role role2 = new Role("USER");
//
//        role1 = roleService.save(role1);
//        role2 = roleService.save(role2);
//
//        User user1 = new User("sethb", "password", "seth.bradshaw@gmail.com", 90002);
//        User user2 = new User("davidc", "password", "david.chang@gmail.com", 90005);
//        User user3 = new User("chrisg", "password", "chris.girvin@gmail.com", 90001);
//
//        user1.getRoles().add(new UserRoles(user1, role1));
//        user2.getRoles().add(new UserRoles(user2, role1));
//        user3.getRoles().add(new UserRoles(user3, role2));
//
//        user1 = userService.save(user1);
//        user2 = userService.save(user2);
//        user3 = userService.save(user3);

        Post post1 = new Post("imgurl", "Dog Poop On Porch", "Someone put dog poop on my porch!", "1234 main St", "find it on your own.", "Clinton", "Utah", 84015);
        Post post2 = new Post("imgurl", "Pot Hole", "There is a pot hole in front of my building. Unacceptable.", "1234 main St", "find it on your own.", "New York City", "New York", 12345);
        Post post3 = new Post("imgurl", "Dirty Park. Save the Enviornment.", "There is trash littered all over this park.", "1234 main St", "It's hot so bring some water", "Queen Creek", "Arizona", 55555);

//        post1 = postService.save(post1);
//        post2 = postService.save(post2);
//        post3 = postService.save(post3);

//        userPostService.save(user1.getUserid(), post1.getPostid());

        Comment com1 = new Comment("This is my comment on this particular issue: great.", userService.findUserById(3), post1);
        Comment com2 = new Comment("This is my comment on this particular issue: this sucks.", userService.findUserById(3), post1);
        Comment com3 = new Comment("This is my comment on this particular issue: awesome.", userService.findUserById(3), post1);

//        com1 = commentService.save(com1);
//        com2 = commentService.save(com2);
//        com3 = commentService.save(com3);



    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void listAllPosts() throws Exception
    {
        String uri = "/posts";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = result.getResponse().getStatus();
        System.out.println(status);
        assertEquals(200, status);
    }

    @Test
    void listPostsByZip()
    {
    }

    @Test
    void findPostById()
    {
    }

    @Test
    void addNewPost()
    {
    }

    @Test
    void editExistingPost()
    {
    }

    @Test
    void deletePost()
    {
    }
}