package com.comake.server.services;

import com.comake.server.models.Post;
import com.comake.server.models.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserPostService
{

    List<Post> findUserPosts(long userid);

    UserPost save(long userid, long postid);
}
