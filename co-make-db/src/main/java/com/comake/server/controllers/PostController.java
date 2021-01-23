package com.comake.server.controllers;

import com.comake.server.models.Comment;
import com.comake.server.models.Post;
import com.comake.server.services.PostService;
import com.comake.server.services.UserPostService;
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

    @Autowired
    UserPostService userPostService;

    @GetMapping(value = "/posts", produces = "application/json")
    public ResponseEntity<?> listAllPosts()
    {
        List<Post> myPosts = postService.findAll();

        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/zipcode/{zipcode}", produces = "application/json")
    public ResponseEntity<?> listPostsByZip(@PathVariable Long zipcode)
    {
        List<Post> myPosts = postService.findPostsByZip(zipcode);

        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/{id}", produces = "application/json")
    public ResponseEntity<?> findPostById(@PathVariable Long id)
    {
        Post post = postService.findPostById(id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping(value = "/posts/{id}/upvotes", produces = "application/json")
    public ResponseEntity<?> getPostsUpvoteCount(@PathVariable long id)
    {


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PostMapping(value = "/posts/post/{id}/comments", consumes = "application/json")
//    public ResponseEntity<?> addNewComment(@RequestBody Comment newComment, @PathVariable long id)
//    {
//        Post post = postService.addNewComment(newComment, id);
//
//        return new ResponseEntity<>(post, HttpStatus.CREATED);
//    }

    @PostMapping(value = "/users/{id}/posts", produces = "application/json")
    public ResponseEntity<?> addNewPost(@PathVariable long id, @Valid @RequestBody Post newPost) throws URISyntaxException
    {
        newPost.setPostid(0);
        newPost = postService.save(newPost);
        userPostService.save(id, newPost.getPostid());

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPostURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postid}")
                .buildAndExpand(newPost.getPostid())
                .toUri();
        responseHeaders.setLocation(newPostURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{userid}/posts/{postid}", produces = "application/json")
    public ResponseEntity<?> editExistingPost(@Valid @RequestBody Post newPost, @PathVariable Long postid, @PathVariable Long userid)
    {
        newPost.setPostid(postid);
        newPost = postService.save(newPost);
        userPostService.save(userid, newPost.getPostid());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/posts/{id}", produces = "application/json")
    public ResponseEntity<?> deletePost(@PathVariable Long id)
    {
        postService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
