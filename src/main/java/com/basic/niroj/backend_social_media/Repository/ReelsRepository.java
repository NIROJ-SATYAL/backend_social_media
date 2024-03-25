package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.Reels;
import com.basic.niroj.backend_social_media.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReelsRepository extends JpaRepository<Reels, Long>{



    public  List<Reels> findByUser(User user);
}
