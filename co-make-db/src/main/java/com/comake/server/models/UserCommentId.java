package com.comake.server.models;

import java.io.Serializable;
import java.util.Objects;

public class UserCommentId implements Serializable
{
    private long user;
    private long comment;

    public UserCommentId()
    {
    }

    public UserCommentId(long user, long comment)
    {
        this.user = user;
        this.comment = comment;
    }

    public long getUser()
    {
        return user;
    }

    public void setUser(long user)
    {
        this.user = user;
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
        UserCommentId that = (UserCommentId) o;
        return getUser() == that.getUser() && getComment() == that.getComment();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(), getComment());
    }
}
