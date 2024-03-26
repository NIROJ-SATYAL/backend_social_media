package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Chat;
import com.basic.niroj.backend_social_media.Model.Message;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageServiceImplementation implements  MessageService{


    @Autowired
    private Userservice userService;
    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public Message sendMessage(Long chatid, Long userid, Message message) throws Exception {

        User user = userService.finduserbyid(userid);
        Chat chat = chatService.getChatById(chatid);

        message.setChat(chat);
        message.setSender(user);
        message.setTimestamp(java.time.LocalDateTime.now());

        try{
            return messageRepository.save(message);
        }
        catch (Exception e){
            throw new Exception("Message not sent");
        }

    }

    @Override
    public List<Message> getMessagesByChat(Long chatid) throws Exception {

        Chat chat = chatService.getChatById(chatid);
        return messageRepository.findByChat(chat);
    }
}
