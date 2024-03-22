package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Post;
import com.basic.niroj.backend_social_media.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository  extends JpaRepository<Post, Long>{
    List<Post> findByUser(User user);
}
