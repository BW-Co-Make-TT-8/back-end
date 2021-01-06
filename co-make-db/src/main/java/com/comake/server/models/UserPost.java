package com.comake.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "userposts")
@IdClass(UserPostId.class)
public class UserPost extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = {"roles", "post", "comment"},
            allowSetters = true)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "postid")
    @JsonIgnoreProperties(value = {"user", "comment"}, allowSetters = true)
    private Post post;

//    @Id
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties(value = {"user", "post"}, allowSetters = true)
//    private Set<Comment> comments = new HashSet<>();

    public UserPost()
    {
    }

    public UserPost(User user, Post post)
    {
        this.user = user;
        this.post = post;
    }

//    public UserPost(User user, Post post, Set<Comment> comments)
//    {
//        this.user = user;
//        this.post = post;
//        this.comments = comments;
//    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Post getPost()
    {
        return post;
    }

    public void setPost(Post post)
    {
        this.post = post;
    }

//    public Set<Comment> getComments()
//    {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments)
//    {
//        this.comments = comments;
//    }

//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserPost userPost = (UserPost) o;
//        return getUser().equals(userPost.getUser()) && getPost().equals(userPost.getPost()) && getComments().equals(userPost.getComments());
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(getUser(), getPost(), getComments());
//    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPost userPost = (UserPost) o;
        return getUser().equals(userPost.getUser()) && getPost().equals(userPost.getPost());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(), getPost());
    }
}
