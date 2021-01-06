package com.comake.server.models;

import java.io.Serializable;
import java.util.Objects;

public class UserPostId implements Serializable
{
    private long user;

    private long post;


    public UserPostId()
    {
    }

    public UserPostId(long user, long post)
    {
        this.user = user;
        this.post = post;
    }

    public long getUser()
    {
        return user;
    }

    public void setUser(long user)
    {
        this.user = user;
    }

    public long getPost()
    {
        return post;
    }

    public void setPost(long post)
    {
        this.post = post;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPostId that = (UserPostId) o;
        return getUser() == that.getUser() && getPost() == that.getPost();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(), getPost());
    }
}
