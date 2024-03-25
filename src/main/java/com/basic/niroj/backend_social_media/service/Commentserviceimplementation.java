package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Comment;
import com.basic.niroj.backend_social_media.Model.Post;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.CommentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;


@Service
public class Commentserviceimplementation implements  Commentservice {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private postservice postservice;

    @Autowired
    private Userservice userservice;




    @Override
    public Comment saveComment(Comment comment, Long postid, Long userid) throws Exception {

        Post post= postservice.getPostById(postid);

        if(post==null)
        {
            throw new Exception("Post not found");
        }

        User user= userservice.finduserbyid(userid);

        if(user==null)
        {
            throw new Exception("User not found");
        }

        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedat(LocalDateTime.now());

        return commentRepository.save(comment);

    }

    @Override
    public Comment likeComment(Long commentid, Long userid) {
        return null;
    }
}
