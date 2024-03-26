package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Message;

import java.util.List;

public interface MessageService {


    public Message sendMessage(Long chatid, Long userid, Message message) throws Exception;



    public List<Message> getMessagesByChat(Long chatid) throws Exception;
}
