package com.comake.server.services;

import com.comake.server.models.Comment;
import com.comake.server.models.Post;

import java.util.List;

public interface PostService
{
    Post save(Post post);

    List<Post> findAll();

    List<Post> findPostsByZip(long zipcode);

    Post findPostById(long id);

    void delete(Long id);

//    Post addNewComment(Comment , long );
}
