package com.comake.server.repository;

import com.comake.server.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long>
{
}
