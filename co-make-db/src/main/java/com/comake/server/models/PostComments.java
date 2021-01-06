package com.comake.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "postcomments")
@IdClass(PostCommentsId.class)
public class PostComments extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "postid")
    @JsonIgnoreProperties(value = {"comment"}, allowSetters = true)
    private Post post;

    @Id
    @ManyToOne
    @JoinColumn(name = "commentid")
    @JsonIgnoreProperties(value = {"user", "post"}, allowSetters = true)
    private Comment comment;

    public PostComments()
    {
    }

    public PostComments(Post post, Comment comment)
    {
        this.post = post;
        this.comment = comment;
    }

    public Post getPost()
    {
        return post;
    }

    public void setPost(Post post)
    {
        this.post = post;
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
        PostComments that = (PostComments) o;
        return getPost().equals(that.getPost()) && getComment().equals(that.getComment());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getPost(), getComment());
    }
}
