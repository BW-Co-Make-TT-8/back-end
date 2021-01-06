package com.comake.server.repository;

import com.comake.server.models.PostComments;
import com.comake.server.models.PostCommentsId;
import org.springframework.data.repository.CrudRepository;

public interface PostCommentRepository extends CrudRepository<PostComments, PostCommentsId>
{

}
