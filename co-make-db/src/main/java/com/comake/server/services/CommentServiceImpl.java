package com.comake.server.services;

import com.comake.server.models.Comment;
import com.comake.server.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService
{
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment)
    {
        Comment newComment = commentRepository.save(comment);

        return newComment;
    }
}
