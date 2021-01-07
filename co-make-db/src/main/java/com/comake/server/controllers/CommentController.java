package com.comake.server.controllers;

import com.comake.server.models.Comment;
import com.comake.server.services.CommentService;
//import com.comake.server.services.PostCommentService;
import com.comake.server.services.PostService;
//import com.comake.server.services.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comments/{id}", produces = "application/json")
    public ResponseEntity<?> getCommentById(@PathVariable long id)
    {
        Comment comment = commentService.findCommentById(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping(value = "/comments", consumes = "application/json")
    public ResponseEntity<?> addNewComment(@RequestBody Comment newComment)
    {
        Comment comment = commentService.save(newComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/comments/{commentid}", consumes = "application/json")
    public ResponseEntity<?> editExistingComment(@PathVariable long commentid, @RequestBody Comment newComment)
    {
        newComment.setCommentid(commentid);
        Comment comment = commentService.save(newComment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/comments/{commentid}")
    public ResponseEntity<?> deleteComment(@PathVariable long commentid)
    {
        commentService.deleteCommentById(commentid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
