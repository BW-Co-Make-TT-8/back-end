package com.comake.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment extends Auditable implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentid;

    @Column(nullable = false)
    private String commentbody;

    @OneToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = {"comments", "roles"}, allowSetters = true)
    private User user;

    @OneToOne
    @JoinColumn(name = "postid")
    @JsonIgnoreProperties(value = {"comments", "post"}, allowSetters = true)
    private Post post;

    public Comment()
    {
    }

    public Comment(String commentbody, User user, Post post)
    {
        this.commentbody = commentbody;
        this.user = user;
        this.post = post;
    }

    public long getCommentid()
    {
        return commentid;
    }

    public void setCommentid(long commentid)
    {
        this.commentid = commentid;
    }

    public String getCommentbody()
    {
        return commentbody;
    }

    public void setCommentbody(String commentbody)
    {
        this.commentbody = commentbody;
    }

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getCommentid() == comment.getCommentid() && getCommentbody().equals(comment.getCommentbody()) && getUser().equals(comment.getUser()) && getPost().equals(comment.getPost());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getCommentid(), getCommentbody(), getUser(), getPost());
    }
}
