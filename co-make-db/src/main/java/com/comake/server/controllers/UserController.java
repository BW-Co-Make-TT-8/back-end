package com.comake.server.controllers;

import com.comake.server.models.Comment;
import com.comake.server.models.Post;
import com.comake.server.models.User;
import com.comake.server.services.CommentService;
import com.comake.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/users",
        produces = "application/json")
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
                HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userid}/comments",
            produces = "application/json")
    public ResponseEntity<?> getUsersComments(
            @PathVariable
                    long userid)
    {
        List<Comment> comments = commentService.findAllUserComments(userid);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}",
        produces = "application/json")
    public ResponseEntity<?> getUserById(
            @PathVariable
                Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    @GetMapping(value = "/users/name/{userName}",
        produces = "application/json")
    public ResponseEntity<?> getUserByName(
            @PathVariable
                String userName)
    {
        User u = userService.findByName(userName);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    @GetMapping(value = "/users/like-name/{userName}",
        produces = "application/json")
    public ResponseEntity<?> getUserLikeName(
            @PathVariable
                String userName)
    {
        List<User> u = userService.findByNameContaining(userName);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    @PutMapping(value = "/users/{userid}",
        consumes = "application/json")
    public ResponseEntity<?> updateFullUser(
            @Valid
            @RequestBody
                User updateUser,
            @PathVariable
                long userid)
    {
        updateUser.setUserid(userid);
        userService.save(updateUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/userinfo",
            produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication)
    {
        User u = userService.findByUsername(authentication.getName());
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }
}
