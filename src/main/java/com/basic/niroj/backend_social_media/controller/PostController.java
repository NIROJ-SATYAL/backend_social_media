package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.Post;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.payload.ApiResponse;
import com.basic.niroj.backend_social_media.payload.UserReponse;
import com.basic.niroj.backend_social_media.service.Userservice;
import com.basic.niroj.backend_social_media.service.postservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private postservice postService;


    @Autowired
    private Userservice userservice;


    @PostMapping("/create")
    private ResponseEntity<ApiResponse> createPost(@RequestBody Post post, @RequestHeader("Authorization") String token) throws Exception {
        Post savepost= postService.CreatePost( post, token);


        if(savepost==null)
        {
           return new ResponseEntity<>( new ApiResponse( "Post not created", false ), HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>( new ApiResponse( "Post created", true ), HttpStatus.OK);
        }
    }


    @GetMapping("/allpost")
  private ResponseEntity<UserReponse> getallpost()  throws  Exception{
        return new ResponseEntity<>(new UserReponse("All post", true,postService.getallpost()), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{postId}")
    private ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Long postId, @RequestHeader("Authorization") String token) throws Exception {
        User user = userservice.finduserbyemail(token);
        Boolean delete = postService.deletePost(postId, user.getId());
        if(delete)
        {
            return new ResponseEntity<>( new ApiResponse( "Post deleted", true ), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>( new ApiResponse( "Post not deleted", false ), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getpostbyuserid/{userId}")

    private ResponseEntity<UserReponse> getPostByUserId(@PathVariable("userId") Long userId) throws Exception {
        return new ResponseEntity<>(new UserReponse("Post by user id", true, postService.getPostByUserId(userId)), HttpStatus.OK);
    }



    @GetMapping("/getpostbyid/{postId}")

    private ResponseEntity<UserReponse> getPostById(@PathVariable("postId") Long postId) throws Exception {
        return new ResponseEntity<>(new UserReponse("Post by id", true, postService.getPostById(postId)), HttpStatus.OK);
    }




    @PutMapping("/savedpost/{postId}")

    private ResponseEntity<ApiResponse> savedpost(@PathVariable("postId") Long postId, @RequestHeader("Authorization") String token) throws Exception {
        User user = userservice.finduserbyemail(token);
        Post post = postService.savedpost(postId, user.getId());
        if(post==null)
        {
            return new ResponseEntity<>( new ApiResponse( "Post not saved", false ), HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>( new ApiResponse( "Post saved", true ), HttpStatus.OK);
        }
    }


    @PutMapping("/likedpost/{postId}")

    private ResponseEntity<ApiResponse>
    LikedPost(@PathVariable("postId") Long postId, @RequestHeader("Authorization") String token) throws Exception {
        User user = userservice.finduserbyemail(token);
        Post post = postService.LikedPost(postId, user.getId());
        if(post==null)
        {
            return new ResponseEntity<>( new ApiResponse( "Post not liked", false ), HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>( new ApiResponse( "Post liked", true ), HttpStatus.OK);
        }
    }

















}
