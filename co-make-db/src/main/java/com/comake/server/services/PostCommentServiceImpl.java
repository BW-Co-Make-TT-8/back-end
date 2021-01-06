package com.comake.server.services;

import com.comake.server.models.*;
import com.comake.server.repository.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "postCommentService")
public class PostCommentServiceImpl implements PostCommentService
{
    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Override
    public PostComments save(long postid, long commentid)
    {
        Post post = postService.findPostById(postid);
        Comment comment = commentService.findCommentById(commentid);
        PostComments postComments = postCommentRepository.findById(new PostCommentsId(post.getPostid(), comment.getCommentid())).orElse(new PostComments(post, comment));

        return postCommentRepository.save(postComments);
    }

    @Override
    public void deleteComment(long postid, long commentid)
    {
        Post post = postService.findPostById(postid);
        Comment comment = commentService.findCommentById(commentid);
        PostComments postComments = postCommentRepository.findById(new PostCommentsId(postid, commentid)).orElseThrow(() -> new EntityNotFoundException("Post with this id was not found"));

        postCommentRepository.deleteById(new PostCommentsId(postid, commentid));
    }
}
