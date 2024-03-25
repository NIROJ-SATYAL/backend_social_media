package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.Story;
import com.basic.niroj.backend_social_media.payload.ApiResponse;
import com.basic.niroj.backend_social_media.payload.UserReponse;
import com.basic.niroj.backend_social_media.service.StoryService;
import com.basic.niroj.backend_social_media.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/story")
public class StoryController {



    @Autowired

    private StoryService storyService;

    @Autowired
    private Userservice userservice;



    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createStory(@RequestBody Story story, @RequestHeader("Authorization") String token)
    {

        try
        {
            Long userid = userservice.finduserbyemail(token).getId();
            storyService.createStory(story, userid);
            return ResponseEntity.ok(new ApiResponse("Story Created", true));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), false));
        }
    }


    @GetMapping("/getallstory")
    public ResponseEntity<UserReponse> getAllStory()
    {
        try
        {
            return ResponseEntity.ok(new UserReponse("All Story", true, storyService.getAllStory()));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new UserReponse(e.getMessage(), false,null));
        }
    }


    @GetMapping("/getstorybyid/{storyid}")

    public ResponseEntity<UserReponse> getStoryById(@PathVariable("storyid") Long storyid)
    {
        try
        {
            return ResponseEntity.ok(new UserReponse("Story", true, storyService.getStoryById(storyid)));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new UserReponse(e.getMessage(), false,null));
        }
    }

    @DeleteMapping("/deletestory/{storyid}")

    public ResponseEntity<ApiResponse> deleteStory(@PathVariable Long storyid, @RequestHeader("Authorization") String token) throws Exception {

            System.out.println("Token: "+token );
            System.out.println("Storyid: "+storyid);
            Long userid = userservice.finduserbyemail(token).getId();

            if(storyService.deleteStory(storyid, userid))
            {
                return ResponseEntity.ok(new ApiResponse("Story Deleted", true));
            }
            else
            {
                return ResponseEntity.ok(new ApiResponse("Story not deleted", false));
            }


    }






}
