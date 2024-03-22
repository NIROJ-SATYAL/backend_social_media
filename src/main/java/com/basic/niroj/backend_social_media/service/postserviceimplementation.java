package com.basic.niroj.backend_social_media.service;


import com.basic.niroj.backend_social_media.Exception.ResourceNotFoundException;
import com.basic.niroj.backend_social_media.Model.Post;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.PostRepository;
import com.basic.niroj.backend_social_media.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
