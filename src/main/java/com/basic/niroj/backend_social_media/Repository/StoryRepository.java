package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Story;
import com.basic.niroj.backend_social_media.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    public List<Story> findByUser(User user);


}
