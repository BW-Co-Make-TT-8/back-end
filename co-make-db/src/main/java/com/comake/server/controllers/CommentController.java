package com.comake.server.controllers;

import com.comake.server.models.Comment;
import com.comake.server.services.CommentService;
import com.comake.server.services.PostCommentService;
import com.comake.server.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private PostService postService;

    @GetMapping(value = "/comments/comment/{id}", produces = "application/json")
    public ResponseEntity<?> getCommentById(@PathVariable long id)
    {
        Comment comment = commentService.findCommentById(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping(value = "/posts/post/{postid}/comments", consumes = "application/json")
    public ResponseEntity<?> addNewComment(@PathVariable long postid, @RequestBody Comment newComment)
    {
        Comment comment = commentService.save(newComment);
        postCommentService.save(postid, comment.getCommentid());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/posts/post/{postid}/comments/{commentid}", consumes = "application/json")
    public ResponseEntity<?> editExistingComment(@PathVariable long postid, @PathVariable long commentid, @RequestBody Comment newComment)
    {
        newComment.setCommentid(commentid);
        Comment comment = commentService.save(newComment);
        postCommentService.save(postid, comment.getCommentid());

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @DeleteMapping(value = "/posts/post/{postid}/comments/{commentid}")
//    public ResponseEntity<?> deleteComment(@PathVariable long postid, @PathVariable long commentid)
//    {
//        commentService.deleteCommentById(commentid);
//        postService.deleteComment(postid, commentid);
//        postCommentService.deleteComment(postid, commentid);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
