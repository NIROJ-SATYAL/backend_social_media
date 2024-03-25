package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.Comment;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.payload.UserReponse;
import com.basic.niroj.backend_social_media.service.Commentservice;
import com.basic.niroj.backend_social_media.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {


    @Autowired
    private Commentservice commentservice;


    @Autowired
    private Userservice users;




    @PostMapping("/createcomment/{postId}")
    private ResponseEntity<UserReponse> createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String token, @PathVariable("postId") Long postId) {

        try {
            User user =users.finduserbyemail(token);
            Comment savecomment = commentservice.saveComment(comment, postId, user.getId());
            return ResponseEntity.ok(new UserReponse("Comment created", true, savecomment));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UserReponse(e.getMessage(), false, null));
        }
    }
}
