package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.Chat;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.payload.ApiResponse;
import com.basic.niroj.backend_social_media.payload.UserReponse;
import com.basic.niroj.backend_social_media.request.CreateChatRequest;
import com.basic.niroj.backend_social_media.service.ChatService;
import com.basic.niroj.backend_social_media.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private Userservice userService;



    @PostMapping("/create")
     public ResponseEntity<UserReponse> createChat(@RequestBody CreateChatRequest createChatRequest, @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.finduserbyemail(token);
        Chat created_chat= chatService.createChat(user.getId(), createChatRequest.getUserid());

        if(created_chat != null){
            return ResponseEntity.ok(new UserReponse("chat created successfully", true, created_chat));
        }else{
            return ResponseEntity.ok(new UserReponse("chat not created", false, null));
        }






    }

    @GetMapping("/get/{chatid}")
    public ResponseEntity<UserReponse> getChat(@PathVariable Long chatid) throws Exception {

        Chat chat = chatService.getChatById(chatid);

        if(chat != null){
            return ResponseEntity.ok(new UserReponse("chat found", true, chat));
        }else{
            return ResponseEntity.ok(new UserReponse("chat not found", false, null));
        }

    }


    @GetMapping("/getbyuser")

    public ResponseEntity<UserReponse> getChatByUser(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.finduserbyemail(token);
        return ResponseEntity.ok(new UserReponse("chat found", true, chatService.getChatByUser(user.getId())));
    }




}
