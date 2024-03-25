package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Exception.ResourceNotFoundException;
import com.basic.niroj.backend_social_media.Model.Reels;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReelsServiceImplementation implements  ReelsService {


    @Autowired

    private ReelsRepository reelsRepository;

    @Autowired
    private Userservice userservice;


    @Override
    public Reels createReels(Reels reels, Long userid) throws Exception {


       User user = userservice.finduserbyid(userid);

        if(user==null)
        {
            throw new ResourceNotFoundException("User not found","id",userid);
        }

        reels.setUser(user);
        reels.setCreatedAt(LocalDateTime.now());

        return reelsRepository.save(reels);



    }

    @Override
    public Reels getReelsById(Long reelsid) throws Exception {
        return null;
    }

    @Override
    public Boolean deleteReels(Long reelsid, Long userid) throws Exception {
        return null;
    }

    @Override
    public List<Reels> getReelsByUser(Long userid) throws Exception {
        return null;
    }

    @Override
    public List<Reels> getAllReels() throws Exception {
        return null;
    }

    @Override
    public Reels likeReels(Long reelsid, Long userid) throws Exception {
        return null;
    }
}
