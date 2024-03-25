package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.Reels;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.payload.ApiResponse;
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


    @GetMapping("/getreelsbyuser/{userid}")
    public ResponseEntity<UserReponse> getReelsByUser(@PathVariable("userid") Long userid)
    {
        try
        {
            return ResponseEntity.ok(new UserReponse("Reels by user",true,reelsService.getReelsByUser(userid)));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new UserReponse(e.getMessage(),false,null));
        }
    }
    @DeleteMapping("/deletereels/{reelsid}")

    public ResponseEntity<ApiResponse> deletereels(@PathVariable Long reelsid, @RequestHeader("Authorization") String token)
    {
        try
        {
            User user= userservice.finduserbyemail(token);
            if(reelsService.deleteReels(reelsid,user.getId()))
            {
                return ResponseEntity.ok(new ApiResponse("Reels deleted",true));
            }
            return ResponseEntity.ok(new ApiResponse("Reels not deleted",false));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(),false));
        }
    }

    @PutMapping("/likereels/{reelsid}")

    public ResponseEntity<UserReponse> likeReels(@PathVariable("reelsid") Long reelsid, @RequestHeader("Authorization") String token)
    {
        try
        {
            User user= userservice.finduserbyemail(token);
            return ResponseEntity.ok(new UserReponse("Reels liked",true,reelsService.likeReels(reelsid,user.getId())));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new UserReponse(e.getMessage(),false,null));
        }
    }

    @GetMapping("/getreelsbyid/{reelsid}")

    public ResponseEntity<UserReponse> getReelsById(@PathVariable("reelsid") Long reelsid)
    {
        try
        {
            return ResponseEntity.ok(new UserReponse("Reels by id",true,reelsService.getReelsById(reelsid)));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new UserReponse(e.getMessage(),false,null));
        }
    }

}
