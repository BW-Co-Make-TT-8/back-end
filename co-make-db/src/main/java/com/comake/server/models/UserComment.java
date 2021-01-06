package com.comake.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usercomments")
@IdClass(UserCommentId.class)
public class UserComment extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = {"comment"}, allowSetters = true)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "commentid")
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Comment comment;

//    @ManyToOne
//    @JoinColumn(name = "postid")
//    @JsonIgnoreProperties(value = {"comments", "user"}, allowSetters = true)
//    private Post post;

    public UserComment()
    {
    }

    public UserComment(User user, Comment comment)
    {
        this.user = user;
        this.comment = comment;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Comment getComment()
    {
        return comment;
    }

    public void setComment(Comment comment)
    {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserComment that = (UserComment) o;
        return getUser().equals(that.getUser()) && getComment().equals(that.getComment());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(), getComment());
    }
}
