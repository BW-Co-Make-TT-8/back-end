package com.comake.server.services;

import com.comake.server.models.Comment;

public interface CommentService
{
    Comment save(Comment comment);

    Comment findCommentById(long id);

//    Comment addNewComment(Comment newComment, long id);
}
