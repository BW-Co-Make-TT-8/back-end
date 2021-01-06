package com.comake.server.models;

import java.io.Serializable;
import java.util.Objects;

public class PostCommentsId implements Serializable
{
    private long post;
    private long comment;

    public PostCommentsId()
    {
    }

    public PostCommentsId(long post, long comment)
    {
        this.post = post;
        this.comment = comment;
    }

    public long getPost()
    {
        return post;
    }

    public void setPost(long post)
    {
        this.post = post;
    }

    public long getComment()
    {
        return comment;
    }

    public void setComment(long comment)
    {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCommentsId that = (PostCommentsId) o;
        return getPost() == that.getPost() && getComment() == that.getComment();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getPost(), getComment());
    }
}
