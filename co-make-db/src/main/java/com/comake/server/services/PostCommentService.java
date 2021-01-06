package com.comake.server.services;

import com.comake.server.models.PostComments;
import com.comake.server.models.UserPost;

public interface PostCommentService
{
    PostComments save(long postid, long commentid);

    void deleteComment(long postid, long commentid);
}
