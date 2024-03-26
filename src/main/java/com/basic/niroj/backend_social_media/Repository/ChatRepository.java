package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long>{
}
