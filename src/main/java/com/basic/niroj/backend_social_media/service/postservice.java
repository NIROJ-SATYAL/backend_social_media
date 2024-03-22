package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Post;

import java.util.List;

public interface postservice {


    Post CreatePost(Post post,Long id) throws Exception;


    public List<Post> getallpost();





}
