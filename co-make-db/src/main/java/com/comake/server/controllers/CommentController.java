package com.comake.server.controllers;

import com.comake.server.models.Comment;
import com.comake.server.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comments")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comment/{id}", produces = "application/json")
    public ResponseEntity<?> getCommentById(@PathVariable long id)
    {
        Comment comment = commentService.findCommentById(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
