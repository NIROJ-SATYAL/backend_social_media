package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Chat;
import com.basic.niroj.backend_social_media.Model.User;

import java.util.List;

public interface ChatService {


    public Chat createChat(Chat chat) throws Exception;

    public Chat getChatById(Long chatid) throws Exception;

    public Boolean deleteChat(Long chatid) throws Exception;

    public List<Chat> getChatByUser(Long userid) throws Exception;

    public List<Chat> getAllChat() throws Exception;

    public Chat addUserToChat(Long chatid, Long userid) throws Exception;
    public Chat removeUserFromChat(Long chatid, Long userid) throws Exception;

    public List<User> getChatUsers(Long chatid) throws Exception;

    public Chat addMessageToChat(Long chatid, Long messageid) throws Exception;
    public Chat removeMessageFromChat(Long chatid, Long messageid) throws Exception;


}
