package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Comment;
import com.basic.niroj.backend_social_media.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByPost(Post post);
}
