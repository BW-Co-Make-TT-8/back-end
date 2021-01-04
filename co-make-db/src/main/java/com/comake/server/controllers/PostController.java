package com.comake.server.controllers;

import com.comake.server.models.Post;
import com.comake.server.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class PostController
{
    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts", produces = "application/json")
    public ResponseEntity<?> listAllPosts()
    {
        List<Post> myPosts = postService.findAll();

        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/{zipcode}", produces = "application/json")
    public ResponseEntity<?> listPostsByZip(@PathVariable Long zipcode)
    {
        List<Post> myPosts = postService.findPostsByZip(zipcode);

        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/post/{id}", produces = "application/json")
    public ResponseEntity<?> findPostById(@PathVariable Long id)
    {
        Post post = postService.findPostById(id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping(value = "/posts", produces = "application/json")
    public ResponseEntity<?> addNewPost(@Valid @RequestBody Post newPost) throws URISyntaxException
    {
        newPost.setPostid(0);
        newPost = postService.save(newPost);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPostURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postid}")
                .buildAndExpand(newPost.getPostid())
                .toUri();
        responseHeaders.setLocation(newPostURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/posts/post/{id}", produces = "application/json")
    public ResponseEntity<?> editExistingPost(@Valid @RequestBody Post newPost, @PathVariable Long id)
    {
        newPost.setPostid(id);
        newPost = postService.save(newPost);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/posts/post/{id}", produces = "application/json")
    public ResponseEntity<?> deletePost(@PathVariable Long id)
    {
        postService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
