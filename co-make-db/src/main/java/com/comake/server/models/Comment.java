package com.comake.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentid;

    @Column(nullable = false)
    private String commentbody;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "comments", allowSetters = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "postid")
    @JsonIgnoreProperties(value = {"comments", "post"}, allowSetters = true)
    private Post post;

    public Comment()
    {
    }

    public Comment(String commentbody)
    {
        this.commentbody = commentbody;
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
}
