package com.comake.server.repository;

import com.comake.server.models.UserPost;
import com.comake.server.models.UserPostId;
import org.springframework.data.repository.CrudRepository;

public interface UserPostRepository extends CrudRepository<UserPost, UserPostId>
{

}
