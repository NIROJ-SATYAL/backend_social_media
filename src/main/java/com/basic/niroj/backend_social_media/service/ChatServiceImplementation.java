package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Exception.ResourceNotFoundException;
import com.basic.niroj.backend_social_media.Model.Chat;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImplementation implements  ChatService{

    @Autowired
   private ChatRepository chatRepository;

    @Autowired
    private Userservice userservice;
    @Override
    public Chat createChat( Long userid,Long requser) throws Exception {

        User user = userservice.finduserbyid(userid);
        User requ = userservice.finduserbyid(requser);

        Chat isExist = chatRepository.findByUsersAndUsers(userservice.finduserbyid(userid), userservice.finduserbyid(requser));

        if (isExist != null) {
            return isExist;
        } else {
            Chat chat = new Chat();
            chat.getUsers().add(userservice.finduserbyid(userid));
            chat.getUsers().add(userservice.finduserbyid(requser));
            chat.setChat_image(user.getFirstName());
            chat.setTimestamp(LocalDateTime.now());
            return chatRepository.save(chat);


        }
    }

    @Override
    public Chat getChatById(Long chatid) throws Exception {

        return chatRepository.findById(chatid).orElseThrow(()-> new ResourceNotFoundException("Chat","id",chatid));
    }

    @Override
    public Boolean deleteChat(Long chatid,Long userid) throws Exception {


        Chat chat = chatRepository.findById(chatid).orElseThrow(()-> new ResourceNotFoundException("Chat","id",chatid));
        User user = userservice.finduserbyid(userid);
       try{

              if(chat.getUsers().contains(user)){
                chat.getUsers().remove(user);
                chatRepository.save(chat);
                return true;
              }
              else{
                  return false;
              }


       }
       catch (Exception e){
           return false;
       }
    }

    @Override
    public List<Chat> getChatByUser(Long userid) throws Exception {


        return chatRepository.findByUsers(userservice.finduserbyid(userid));
    }



    @Override
    public Chat addUserToChat(Long chatid, Long userid, Long addeduserid) throws Exception {

        Chat chat = chatRepository.findById(chatid).orElseThrow(()-> new ResourceNotFoundException("Chat","id",chatid));
        if(
                chat.getUsers().contains(userservice.finduserbyid(userid))
        ) {
            if(chat.getUsers().contains(userservice.finduserbyid(addeduserid))){
                return chat;
            }
            else{
                chat.getUsers().add(userservice.finduserbyid(addeduserid));
                return chatRepository.save(chat);
            }
        }
        else{
           return chat;
        }



    }

    @Override
    public Chat removeUserFromChat(Long chatid, Long userid) throws Exception {

        Chat chat = chatRepository.findById(chatid).orElseThrow(()-> new ResourceNotFoundException("Chat","id",chatid));
        chat.getUsers().remove(userservice.finduserbyid(userid));
        return chatRepository.save(chat);
    }


}
