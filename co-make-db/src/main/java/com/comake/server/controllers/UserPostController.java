package com.comake.server.controllers;

import com.comake.server.models.Post;
import com.comake.server.services.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserPostController
{
    @Autowired
    private UserPostService userPostService;

    @GetMapping(value = "/users/{userid}/posts", produces = "application/json")
    public ResponseEntity<?> getUserPosts(@PathVariable long userid)
    {
        List<Post> posts = userPostService.findUserPosts(userid);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
