package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long>
{


}
