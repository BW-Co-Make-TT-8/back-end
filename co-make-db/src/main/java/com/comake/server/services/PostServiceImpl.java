package com.comake.server.services;

import com.comake.server.models.Post;
import com.comake.server.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "postService")
public class PostServiceImpl implements PostService
{
    @Autowired
    PostRepository postRepository;

    @Override
    public Post save(Post post)
    {
        Post newPost = postRepository.save(post);
        return newPost;
    }
}
