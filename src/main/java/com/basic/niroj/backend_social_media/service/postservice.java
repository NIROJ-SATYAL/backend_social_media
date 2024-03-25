package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Comment;
import com.basic.niroj.backend_social_media.Model.Post;

import java.util.List;

public interface postservice {


    Post CreatePost(Post post, String token) throws Exception;


    public List<Post> getallpost() throws  Exception;



    public Boolean deletePost(Long postId,Long userId) throws Exception;

    public List<Post> getPostByUserId(Long userId) throws Exception;

    public Post getPostById(Long postId) throws Exception;

    public Post savedpost (Long postId, Long userId) throws Exception;


    public Post LikedPost(Long postId, Long userId) throws Exception;


    List<Comment> getallcomment(Long postId);
}
