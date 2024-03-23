package com.basic.niroj.backend_social_media.controller;

import com.basic.niroj.backend_social_media.Config.JwtProvider;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.userRepository;
import com.basic.niroj.backend_social_media.payload.ApiResponse;
import com.basic.niroj.backend_social_media.payload.AuthResponse;
import com.basic.niroj.backend_social_media.request.LoginRequest;
import com.basic.niroj.backend_social_media.service.Userservice;
import com.basic.niroj.backend_social_media.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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



@Autowired
private CustomUserDetailsService customUserDetailsService;


    @PostMapping("/register")

    public ResponseEntity<ApiResponse> addUser(@RequestBody User user){
        User users= userrepository.findByEmail(user.getEmail());
        if(users!=null){
           return new ResponseEntity<>(new ApiResponse("User already exist", false), org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        User user1=new User();

        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setGender(user.getGender());
        User saveduser= userservice.registeruser(user1);
        System.out.println(saveduser);
        System.out.println(saveduser.getPassword());
        return new ResponseEntity<>(new ApiResponse("User registered successfully", true), org.springframework.http.HttpStatus.CREATED);








    }





    @PostMapping("/login")

    public AuthResponse Login(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication=passwordchecker(loginRequest.getEmail(), loginRequest.getPassword());
        String token= JwtProvider.GenerateToken(authentication);

        System.out.println(token);

        if(token!=null){
            return new AuthResponse("Login Successfully",true, token);
        }
        else{
            return new AuthResponse("Login failed", false, null);
        }

    }

    private Authentication passwordchecker(String email, String password) {
        System.out.println(email);
        System.out.println(password);
        UserDetails userDetails= customUserDetailsService.loadUserByUsername(email);


        if(userDetails==null){
            throw  new BadCredentialsException("User not found");
        }


        if(passwordEncoder.matches(password, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        else{
            throw new BadCredentialsException("Invalid password");
        }
    }


}
