package com.basic.niroj.backend_social_media.service;


import com.basic.niroj.backend_social_media.Exception.ResourceNotFoundException;
import com.basic.niroj.backend_social_media.Model.Post;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.PostRepository;
import com.basic.niroj.backend_social_media.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class postserviceimplementation  implements  postservice{


    @Autowired
private PostRepository postrepository;


    @Autowired
    private userRepository userrepository;
    @Override
    public Post CreatePost(Post post, Long id) throws Exception {
     User user =  userrepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user", "id", id));


     if(user==null)
     {
            throw new ResourceNotFoundException("user", "id", id);
        }
        else
        {
            post.setUser(user);
            post.setImage("Default.jpg");
            post.setVideo("Default.mp4");
            post.setCreatedat(java.time.LocalDateTime.now());
            Post savepost = postrepository.save(post);
            return savepost;
     }
    }


    public List<Post> getallpost() throws  Exception{
        return postrepository.findAll();
    }

    @Override
    public Boolean deletePost(Long postId, Long userId) throws Exception {
        Post post = postrepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
        User user = userrepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        if(post.getUser().getId().equals(user.getId()))
        {
            postrepository.delete(post);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<Post> getPostByUserId(Long userId) throws Exception {

        User user = userrepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        return postrepository.findByUser(user);
    }

    @Override
    public Post getPostById(Long postId) throws Exception {
        return null;
    }
}
