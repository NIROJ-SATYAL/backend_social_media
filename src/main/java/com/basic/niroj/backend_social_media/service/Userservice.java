package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.User;

import java.util.List;

public interface Userservice {


    public User registeruser(User user);



    public User finduserbyid(Long id) throws Exception;

    public User finduserbyemail(String email) throws Exception;
    public User followuser(Long id, Long id2) throws Exception;

    public User updateuser(  User user, Long id) throws Exception;


    public List<User> seraechuser(String query) throws Exception;

}
