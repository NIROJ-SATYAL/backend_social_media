package com.basic.niroj.backend_social_media.controller;


import com.basic.niroj.backend_social_media.Model.Message;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.payload.UserReponse;
import com.basic.niroj.backend_social_media.service.MessageService;
import com.basic.niroj.backend_social_media.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {


    @Autowired
    private Userservice userService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/send/{chatid}")

    public ResponseEntity<UserReponse> sendMessage(@PathVariable Long chatid, @RequestBody Message message, @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.finduserbyemail(token);
        Message created_message = messageService.sendMessage(chatid, user.getId(), message);

        if(created_message != null){
            return ResponseEntity.ok(new UserReponse("message sent successfully", true, created_message));
        }else{
            return ResponseEntity.ok(new UserReponse("message not sent", false, null));
        }

    }


    @GetMapping("/get/{chatid}")

    public ResponseEntity<UserReponse> getMessages(@PathVariable Long chatid) throws Exception {

        return ResponseEntity.ok(new UserReponse("messages found", true, messageService.getMessagesByChat(chatid)));

    }
}
