package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface userRepository extends JpaRepository<User, Long>{

}
