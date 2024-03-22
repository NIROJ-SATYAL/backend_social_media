package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository  extends JpaRepository<Post, Long>{
}
