package com.comake.server.services;

import com.comake.server.ServerApplication;
import com.comake.server.ServerApplicationTests;
import com.comake.server.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UserServiceImplTest
{
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void a_findUserById()
    {
        assertEquals("davidc", userService.findUserById(4).getUsername());
    }

    @Test
    public void b_findByNameContaining()
    {
        assertEquals(1, userService.findByNameContaining("dav").size());
    }

    @Test
    public void c_findAll()
    {
        assertEquals(3, userService.findAll().size());
    }

    @Test
    public void d_findByName()
    {
        assertEquals("davidc", userService.findByName("davidc").getUsername());
    }

    @Test
    public void e_save()
    {
        User u4 = new User();
        u4.setUsername("test123");
        u4.setPasswordNoEncrypt("password");
        u4.setEmail("test123@gmail.com");
        User addUser = userService.save(u4);
        assertEquals("test123", addUser.getUsername());
    }

    @Test
    public void f_delete()
    {
        userService.delete(4);
        assertEquals(3, userService.findAll().size());
    }

    @Test
    public void g_deleteAll()
    {
        userService.deleteAll();
        assertEquals(0, userService.findAll().size());
    }
}