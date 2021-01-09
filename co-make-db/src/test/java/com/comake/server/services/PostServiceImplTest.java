package com.comake.server.services;

import com.comake.server.ServerApplication;
import com.comake.server.models.Post;
import com.comake.server.models.UserPost;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PostServiceImplTest
{
    @Autowired
    private PostService postService;

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
    public void save()
    {
        String newPostTitle = "Huge Pot Hole Down My Street";
        Post newPost = new Post();
        newPost.setImgurl("imgurl");
        newPost.setTitle(newPostTitle);
        newPost.setPostbody("There is a huge pot hole on my street and I'm furious! I got a flat tire because of your incompetence.");
        newPost.setStreetaddress("1234 Nonya Business");
        newPost.setAddressnotes("Its the first house on the corner with tigers in the backyard.");
        newPost.setCity("Clinton");
        newPost.setState("Ut");
        newPost.setLocation(84015);
//        newPost.getUserPosts().add(new UserPost(userService.findUserById(3), newPost));


        Post addedPost = postService.save(newPost);
        assertNotNull(addedPost);
        assertEquals(newPost.getTitle(), newPostTitle);
    }

    @Test
    public void a_findAll()
    {
        List<Post> postsList = postService.findAll();

        assertEquals(postsList.size(), 3);
    }

    @Test
    public void b_findPostsByZip()
    {
        List<Post> postsList = postService.findPostsByZip(84015);

        assertEquals(postsList.size(), 2);
    }

    @Test
    public void findPostById()
    {
        Post post = postService.findPostById(6);

        assertEquals(post.getLocation(), 84015);
    }

    @Test
    public void c_delete()
    {
        postService.delete((long)6);

        assertEquals(postService.findAll().size(), 3);
    }

    @Test
    public void deleteComment()
    {

    }
}