package com.comake.server.services;

import com.comake.server.models.Comment;

import java.util.List;

public interface CommentService
{
    Comment save(Comment comment);

    Comment findCommentById(long id);

    void deleteCommentById(long commentid);

    List<Comment> findAllUserComments(long userid);

//    Comment addNewComment(Comment newComment, long id);
}
