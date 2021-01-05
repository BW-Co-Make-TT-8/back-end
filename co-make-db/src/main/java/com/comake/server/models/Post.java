package com.comake.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postid;

    private String imgurl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String postbody;

    @Column(nullable = false)
    private String streetaddress;

    private String addressnotes;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

//    @ManyToOne
//    @JoinColumn(name = "locationid", nullable = false)
//    @JsonIgnoreProperties(value = "posts", allowSetters = true)
//    private Location location;
    @Column(nullable = false)
    private long location;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = {"posts", "comments"}, allowSetters = true)
    private User user;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"post", "user"}, allowSetters = true)
    private List<Comment> comments = new ArrayList<>();

    public Post()
    {
    }

    public Post(String imgurl, String title, String postbody, String streetaddress, String addressnotes, String city, String state, long location, User user)
    {
        this.imgurl = imgurl;
        this.title = title;
        this.postbody = postbody;
        this.streetaddress = streetaddress;
        this.addressnotes = addressnotes;
        this.city = city;
        this.state = state;
        this.location = location;
        this.user = user;
    }

    public long getPostid()
    {
        return postid;
    }

    public void setPostid(long postid)
    {
        this.postid = postid;
    }

    public String getImgurl()
    {
        return imgurl;
    }

    public void setImgurl(String imgurl)
    {
        this.imgurl = imgurl;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPostbody()
    {
        return postbody;
    }

    public void setPostbody(String postbody)
    {
        this.postbody = postbody;
    }

    public String getStreetaddress()
    {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress)
    {
        this.streetaddress = streetaddress;
    }

    public String getAddressnotes()
    {
        return addressnotes;
    }

    public void setAddressnotes(String addressnotes)
    {
        this.addressnotes = addressnotes;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public long getLocation()
    {
        return location;
    }

    public void setLocation(long location)
    {
        this.location = location;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }
}
