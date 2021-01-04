package com.comake.server.controllers;

import com.comake.server.models.User;
import com.comake.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

//    Return a list of all users
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/users",
        produces = "application/json")
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
                HttpStatus.OK);
    }

//    Given a complete User Object, create a new User
    @PostMapping(value = "/users",
        consumes = "application/json")
    public ResponseEntity<?> addNewUser(
            @Valid
            @RequestBody
                User newuser) throws
                                URISyntaxException
    {
        newuser.setUserid(0);
        newuser = userService.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

//    Given an userid, return a user
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

//    Given a username, return a user
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

    // Given an id and a json body, edit a specific user
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

}
