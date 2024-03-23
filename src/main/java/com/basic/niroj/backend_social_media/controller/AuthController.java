package com.basic.niroj.backend_social_media.controller;

import com.basic.niroj.backend_social_media.Config.JwtProvider;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.userRepository;
import com.basic.niroj.backend_social_media.payload.ApiResponse;
import com.basic.niroj.backend_social_media.payload.AuthResponse;
import com.basic.niroj.backend_social_media.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private Userservice userservice;


    @Autowired
    private userRepository userrepository;


@Autowired
private PasswordEncoder passwordEncoder;


    @PostMapping("/register")

    public String addUser(@RequestBody User user){





        User users= userrepository.findByEmail(user.getEmail());


        if(users!=null){
            return "User already exists";
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));


        User saveduser= userservice.registeruser(user);
        System.out.println(saveduser);


        Authentication authentication = new UsernamePasswordAuthenticationToken( saveduser.getEmail(),saveduser.getPassword());
        String token= JwtProvider.GenerateToken(authentication);

        System.out.println(token);

            return token;



    }





}
