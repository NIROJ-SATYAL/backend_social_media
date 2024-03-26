package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Chat;
import com.basic.niroj.backend_social_media.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long>
{
public List<Message> findByChat(Chat chat);

}
