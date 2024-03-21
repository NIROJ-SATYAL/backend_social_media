package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class HomeController {



    @Autowired
    private userRepository userrepository;



    @PostMapping("/adduser")

    private User addUser(@RequestBody User user){
        User saveuser= userrepository.save(user);
        return saveuser;
    }


    @GetMapping("/getuser/{id}")

    private User getUser(@PathVariable Long id) throws Exception{
        Optional<User> user = userrepository.findById(id);

        if(user.isPresent()){
            return user.get();
        }
        else{
    throw new Exception("User not found");
        }

    }


    @PutMapping("/updateuser/{id}")


    private String updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> user1 = userrepository.findById(id);
        if(user1.isPresent()){
            User user2 = user1.get();
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
            userrepository.save(user2);

            return "User updated";
        }
        else{
            return "User not found";
        }
    }





}
