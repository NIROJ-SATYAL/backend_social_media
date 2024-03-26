package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Chat;
import com.basic.niroj.backend_social_media.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long>{


    public List<Chat> findByUsers(User user);


    @Query("select c from Chat c where :user member of c.users and :requser member of c.users")
    public Chat findByUsersAndUsers(@Param("user") User user1, @Param("requser") User requser);
}
