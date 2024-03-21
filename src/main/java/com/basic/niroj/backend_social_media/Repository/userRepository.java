package com.basic.niroj.backend_social_media.Repository;

import com.basic.niroj.backend_social_media.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface userRepository extends JpaRepository<User, Long>{

    public User findByEmail(String email);


    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%" )
    public List<User> searchUser(@Param("query") String query);
}
