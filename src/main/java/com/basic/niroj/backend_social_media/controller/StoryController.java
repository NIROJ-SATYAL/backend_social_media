package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.Story;
import com.basic.niroj.backend_social_media.Model.User;
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
    public ResponseEntity<ApiResponse> createStory(@RequestBody Story story, @RequestHeader("Authorization") String token) {

        try {
            Long userid = userservice.finduserbyemail(token).getId();
            storyService.createStory(story, userid);
            return ResponseEntity.ok(new ApiResponse("Story Created", true));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), false));
        }
    }


    @GetMapping("/getallstory")
    public ResponseEntity<UserReponse> getAllStory() {
        try {
            return ResponseEntity.ok(new UserReponse("All Story", true, storyService.getAllStory()));
        } catch (Exception e) {
            return ResponseEntity.ok(new UserReponse(e.getMessage(), false, null));
        }
    }


    @GetMapping("/getstorybyid/{storyid}")

    public ResponseEntity<UserReponse> getStoryById(@PathVariable("storyid") Long storyid) {
        try {
            return ResponseEntity.ok(new UserReponse("Story", true, storyService.getStoryById(storyid)));
        } catch (Exception e) {
            return ResponseEntity.ok(new UserReponse(e.getMessage(), false, null));
        }
    }

    @DeleteMapping("/deletestory/{storyid}")

    public ResponseEntity<ApiResponse> deleteStory(@PathVariable Long storyid, @RequestHeader("Authorization") String token) throws Exception {

        System.out.println("Token: " + token);
        System.out.println("Storyid: " + storyid);
        Long userid = userservice.finduserbyemail(token).getId();

        if (storyService.deleteStory(storyid, userid)) {
            return ResponseEntity.ok(new ApiResponse("Story Deleted", true));
        } else {
            return ResponseEntity.ok(new ApiResponse("Story not deleted", false));
        }


    }


    @PutMapping("/likestory/{storyid}")

    public ResponseEntity<UserReponse> likeStory(@PathVariable("storyid") Long storyid, @RequestHeader("Authorization") String token) {
        try {
            User user = userservice.finduserbyemail(token);

            return ResponseEntity.ok(new UserReponse("Story liked", true, storyService.likeStory(storyid, user.getId())));
        } catch (Exception e) {
            return ResponseEntity.ok(new UserReponse(e.getMessage(), false, null));
        }
    }

    @GetMapping("/getstorybyuser/{userid}")

    public ResponseEntity<UserReponse> getStoryByUser(@PathVariable("userid") Long userid) {
        try {
            return ResponseEntity.ok(new UserReponse("Story by user", true, storyService.getStoryByUser(userid)));
        } catch (Exception e) {
            return ResponseEntity.ok(new UserReponse(e.getMessage(), false, null));
        }
    }

    @PutMapping("/viewstory/{storyid}")
    public ResponseEntity<ApiResponse> viewStory(@PathVariable("storyid") Long storyid, @RequestHeader("Authorization") String token) {
        try {
            User user = userservice.finduserbyemail(token);
            storyService.viewStory(storyid, user.getId());
            return ResponseEntity.ok(new ApiResponse("Story viewed", true));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), false));
        }
    }

    @GetMapping("/getlikestory/{storyid}")

    public ResponseEntity<UserReponse> getAllLikeStory(@RequestHeader("Authorization") String token, @PathVariable("storyid") Long storyid) {
        try {
            User user = userservice.finduserbyemail(token);
            return ResponseEntity.ok(new UserReponse("All liked story", true, storyService.getStoryLikers(user.getId(), storyid)));
        } catch (Exception e) {
            return ResponseEntity.ok(new UserReponse(e.getMessage(), false, null));
        }
    }

    {

    }

    @GetMapping("/getviewstory/{storyid}")

    public ResponseEntity<UserReponse> getAllViewStory(@RequestHeader("Authorization") String token, @PathVariable("storyid") Long storyid) {
        try {
            User user = userservice.finduserbyemail(token);
            return ResponseEntity.ok(new UserReponse("All viewed story", true, storyService.getStoryViewers(storyid, user.getId())));


        } catch (Exception e) {
            return ResponseEntity.ok(new UserReponse(e.getMessage(), false, null));
        }


    }

}
