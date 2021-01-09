package com.comake.server.services;

import com.comake.server.ServerApplication;
import com.comake.server.models.Comment;
import com.comake.server.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
class CommentServiceImplTest
{
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

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
        Comment comment = new Comment();
        comment.setCommentbody("This is my comment test.");
        comment.setUser(userService.findUserById(3));
        comment.setPost(postService.findPostById(6));
        commentService.save(comment);

        assertEquals(commentService.findCommentById(12).getCommentbody(), comment.getCommentbody());
    }

    @Test
    public void findCommentById()
    {
        Comment comment = commentService.findCommentById(11);

        assertEquals(comment.getUser().getUserid(), 3);
    }

    @Test
    public void deleteCommentById()
    {
        commentService.deleteCommentById(10);

        assertEquals(commentService.findAllUserComments(3).size(), 2);
    }

    @Test
    public void findAllUserComments()
    {
        List<Comment> commentsList = commentService.findAllUserComments(3);

        assertEquals(commentsList.size(), 2);
    }
}