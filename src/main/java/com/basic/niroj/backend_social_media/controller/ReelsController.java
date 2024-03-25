package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.Reels;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.payload.UserReponse;
import com.basic.niroj.backend_social_media.service.ReelsService;
import com.basic.niroj.backend_social_media.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reels")
public class ReelsController
{



    @Autowired
    private ReelsService reelsService;

    @Autowired
    private Userservice userservice;


    @PostMapping("/createreels")
    public ResponseEntity<UserReponse> createReels(@RequestHeader("Authorization") String token, @RequestBody Reels reels)
    {

            try
            {
                User user= userservice.finduserbyemail(token);
                return ResponseEntity.ok(new UserReponse("Reels created",true,reelsService.createReels(reels,user.getId())));
            }
            catch (Exception e)
            {
                return ResponseEntity.badRequest().body(new UserReponse(e.getMessage(),false,null));
            }

    }


    @GetMapping("/getallreels")

    public ResponseEntity<UserReponse> getAllReels()
    {
        try
        {
            return ResponseEntity.ok(new UserReponse("All reels",true,reelsService.getAllReels()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new UserReponse(e.getMessage(),false,null));
        }
    }

}
