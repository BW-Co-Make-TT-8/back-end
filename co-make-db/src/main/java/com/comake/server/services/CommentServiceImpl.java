package com.comake.server.services;

import com.comake.server.models.Comment;
import com.comake.server.models.Post;
import com.comake.server.repository.CommentRepository;
import com.comake.server.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService
{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment save(Comment comment)
    {
        Comment newComment = commentRepository.save(comment);

        return newComment;
    }

    @Override
    public Comment findCommentById(long id)
    {
        Comment comment;
        comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found."));

        return comment;
    }

    @Override
    public void deleteCommentById(long commentid)
    {
        commentRepository.deleteById(commentid);
    }

//    @Override
//    public Comment addNewComment(Comment newComment, long id)
//    {
//        Comment comment = new Comment();
//        comment.setCommentbody(newComment.getCommentbody());
//        commentRepository.save(comment);
//        Post post;
//        post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post with id " + id + " not found."));
//        post.getComments().add(comment);
//        postRepository.save(post);
//
//        return comment;
//    }
}
