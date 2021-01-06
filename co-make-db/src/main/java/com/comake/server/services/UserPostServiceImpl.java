package com.comake.server.services;

import com.comake.server.models.Post;
import com.comake.server.models.User;
import com.comake.server.models.UserPost;
import com.comake.server.models.UserPostId;
import com.comake.server.repository.UserPostRepository;
import com.comake.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userPostService")
public class UserPostServiceImpl implements UserPostService
{
    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Override
    public UserPost save(long userid, long postid)
    {
        Post post = postService.findPostById(postid);
        User user = userService.findUserById(userid);
        UserPost userPost = userPostRepository.findById(new UserPostId(user.getUserid(), post.getPostid())).orElse(new UserPost(user, post));

        return userPostRepository.save(userPost);
    }

    @Override
    public List<Post> findUserPosts(long userid)
    {
        List<UserPost> userPosts = new ArrayList<>();
        userPostRepository.findAll().iterator().forEachRemaining(userPosts::add);
        List<Post> posts = new ArrayList<>();
        for (UserPost pr : userPosts)
        {
            if (pr.getUser().getUserid() == userid)
            {
                posts.add(pr.getPost());
            }
        }

        return posts;
    }
}
