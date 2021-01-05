package com.comake.server.services;

import com.comake.server.exceptions.ResourceNotFoundException;
import com.comake.server.models.Comment;
import com.comake.server.models.Location;
import com.comake.server.models.Post;
import com.comake.server.repository.CommentRepository;
import com.comake.server.repository.LocationRepository;
import com.comake.server.repository.PostRepository;
import com.mifmif.common.regex.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "postService")
public class PostServiceImpl implements PostService
{
    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public Post save(Post post)
    {
        Post newPost = new Post();

        if (post.getPostid() != 0)
        {
            postRepository.findById(post.getPostid())
                    .orElseThrow(() -> new ResourceNotFoundException("Post with id " + post.getPostid() + " not found."));
            newPost.setPostid(post.getPostid());
        }

        newPost.setImgurl(post.getImgurl());
        newPost.setTitle(post.getTitle());
        newPost.setPostbody(post.getPostbody());
        newPost.setStreetaddress(post.getStreetaddress());
        newPost.setAddressnotes(post.getAddressnotes());
        newPost.setCity(post.getCity());
        newPost.setState(post.getState());
        newPost.setLocation(post.getLocation());
//        for (Location l : locationRepository.findAll())
//        {
//            Location location = new Location();
//            if (post.getLocation().getZipcode() == l.getZipcode())
//            {
//                newPost.setLocation(post.getLocation());
//            }
////            else if (post.getLocation().getZipcode() != l.getZipcode())
////            {
////
////            }
//              else
//            {
//                location.setZipcode(l.getZipcode());
//                locationRepository.save(location);
//            }
//        }

        newPost.setUser(post.getUser());

        for (Comment c : post.getComments())
        {
            Comment newComment = commentRepository.findById(c.getCommentid())
                    .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + c.getCommentid() + " not found."));
            newPost.getComments().add(newComment);
        }

        return postRepository.save(newPost);
    }

    @Override
    public List<Post> findAll()
    {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().iterator().forEachRemaining(posts::add);

        return posts;
    }

    @Override
    public List<Post> findPostsByZip(long zipcode)
    {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().iterator().forEachRemaining(posts::add);

        List<Post> postsinzip = new ArrayList<>();
        for (Post p : posts)
        {
            if(p.getLocation() == zipcode)
            {
                postsinzip.add(p);
            }
        }

        return postsinzip;
    }

    @Override
    public Post findPostById(long id)
    {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found."));
    }

    @Override
    public void delete(Long id)
    {
        postRepository.deleteById(id);
    }
}
