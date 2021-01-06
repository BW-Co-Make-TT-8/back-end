package com.comake.server.controllers;

import com.comake.server.models.Comment;
import com.comake.server.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comments/comment/{id}", produces = "application/json")
    public ResponseEntity<?> getCommentById(@PathVariable long id)
    {
        Comment comment = commentService.findCommentById(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping(value = "/posts/post/{id}/comments", consumes = "application/json")
    public ResponseEntity<?> addNewComment(@RequestBody Comment newComment, @PathVariable long id)
    {
        Comment comment = commentService.save(newComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
