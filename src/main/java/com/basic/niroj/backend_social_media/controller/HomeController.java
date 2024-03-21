package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.userRepository;
import com.basic.niroj.backend_social_media.payload.ApiResponse;
import com.basic.niroj.backend_social_media.payload.UserReponse;
import com.basic.niroj.backend_social_media.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class HomeController {


@Autowired
private Userservice userservice;



    @PostMapping("/adduser")

    public ResponseEntity<ApiResponse> addUser(@RequestBody User user){



       User saveduser= userservice.registeruser(user);

       if(saveduser!=null){
           return new ResponseEntity<>(new ApiResponse("User added", true), HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(new ApiResponse("User not added", false), HttpStatus.BAD_REQUEST);
       }

    }


    @GetMapping("/getuser/{id}")

    private ResponseEntity<UserReponse> getUser(@PathVariable Long id) throws Exception{
       User user = userservice.finduserbyid(id);

       if(user!=null){
           return new ResponseEntity<>(new UserReponse("User found", true, user), HttpStatus.OK);
       }
       else{
              return new ResponseEntity<>(new UserReponse("User not found", false, null), HttpStatus.BAD_REQUEST);
       }

    }


    @PutMapping("/updateuser/{id}")


    private ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody User user){

        try{
            userservice.updateuser(user, id);
            return new ResponseEntity<>(new ApiResponse("User updated", true), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new ApiResponse("User not updated", false), HttpStatus.BAD_REQUEST);
        }

    }





    @GetMapping("/getuserbyemail/{email}")
    private ResponseEntity<UserReponse> getUserByEmail(@PathVariable String email) throws Exception{
       User user = userservice.finduserbyemail(email);

       if(user!=null){
           return new ResponseEntity<>(new UserReponse("User found", true, user), HttpStatus.OK);
       }
       else{
              return new ResponseEntity<>(new UserReponse("User not found", false, null), HttpStatus.BAD_REQUEST);
       }

    }


    @PutMapping("/followuser/{id}/{id2}")
    private ResponseEntity<UserReponse> followUser(@PathVariable Long id, @PathVariable Long id2) throws Exception{
       User user = userservice.followuser(id, id2);

       if(user!=null){
           return new ResponseEntity<>(new UserReponse("User found", true, user), HttpStatus.OK);
       }
       else{
              return new ResponseEntity<>(new UserReponse("User not found", false, null), HttpStatus.BAD_REQUEST);
       }

    }


    @GetMapping("/searchuser/{query}")
    private ResponseEntity<UserReponse> searchUser(@PathVariable String query) throws Exception{
        System.out.println(query);
      List< User> user = userservice.seraechuser(query);

       if(user!=null){
           return new ResponseEntity<>(new UserReponse("User found", true, user), HttpStatus.OK);
       }
       else{
              return new ResponseEntity<>(new UserReponse("User not found", false, null), HttpStatus.BAD_REQUEST);
       }

    }




}
