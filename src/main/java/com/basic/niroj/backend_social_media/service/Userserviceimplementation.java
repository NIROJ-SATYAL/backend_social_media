package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Exception.ResourceNotFoundException;
import com.basic.niroj.backend_social_media.Model.Post;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Userserviceimplementation implements  Userservice{



    @Autowired

    private userRepository userrepository;

    @Override
    public User registeruser(User user) {

        User saveuser= userrepository.save(user);
        return saveuser;
    }

    @Override
    public User finduserbyid(Long id) throws Exception {
        User user = userrepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user", "id", id));
        return user;
    }

    @Override
    public User finduserbyemail(String email) throws Exception {
        User user = userrepository.findByEmail(email);
        return user;
    }

    @Override
    public User followuser(Long id, Long id2) throws Exception {
        User user1=finduserbyid(id);
        User user2=finduserbyid(id2);

        user1.getFollowing().add(id2);
        user2.getFollowers().add(id);

        userrepository.save(user1);
        userrepository.save(user2);

 return user1;


    }

    @Override
    public User updateuser( User user, Long id) throws Exception {
        User user2 =finduserbyid(id);


            if(user.getFirstName()!=null && user.getFirstName()!="" ){
                user2.setFirstName(user.getFirstName());
            }
            if(user.getLastName()!=null && user.getLastName()!=""){
                user2.setLastName(user.getLastName());
            }
            if(user.getEmail()!=null  && user.getEmail()!=""){
                user2.setEmail(user.getEmail());
            }
            if(user.getPassword()!=null  && user.getPassword()!="" ){
                user2.setPassword(user.getPassword());
            }
             return userrepository.save(user2);

    }

    @Override
    public List<User> seraechuser(String query) throws Exception {
        List<User> user = userrepository.searchUser(query);
        return user;
    }

    @Override
    public List<Post> savedpost(Long userid) {

        User user = userrepository.findById(userid).orElseThrow(()-> new ResourceNotFoundException("user", "id", userid));
        return user.getSavedpost();
    }

    @Override
    public List<Post> Allpost(Long userid) {
       User user = userrepository.findById(userid).orElseThrow(()-> new ResourceNotFoundException("user", "id", userid));
        return user.getPosts();
    }
}
