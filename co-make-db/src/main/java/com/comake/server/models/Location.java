package com.comake.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "locations")
public class Location extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long locationid;

    @Column(nullable = false, unique = true)
    private long zipcode;

//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties(value = "location", allowSetters = true)
//    private List<User> users;

//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties(value = "location", allowSetters = true)
//    private List<Post> posts = new ArrayList<>();

    public Location()
    {
    }

    public Location(long zipcode)
    {
        this.zipcode = zipcode;
    }

    public long getLocationid()
    {
        return locationid;
    }

    public void setLocationid(long locationid)
    {
        this.locationid = locationid;
    }

    public long getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(long zipcode)
    {
        this.zipcode = zipcode;
    }

//    public List<User> getUsers()
//    {
//        return users;
//    }
//
//    public void setUsers(List<User> users)
//    {
//        this.users = users;
//    }

//    public List<Post> getPosts()
//    {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts)
//    {
//        this.posts = posts;
//    }
}
