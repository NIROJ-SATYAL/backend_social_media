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

        if(user.getId()==userid)
        {


            reels.setUser(user);
            reels.setCreatedAt(LocalDateTime.now());

            return reelsRepository.save(reels);

        }
        throw new ResourceNotFoundException("User not found","id",userid);




    }

    @Override
    public Reels getReelsById(Long reelsid) throws Exception {

        try
        {
            return reelsRepository.findById(reelsid).get();
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Reels not found","id",reelsid);
        }
    }

    @Override
    public Boolean deleteReels(Long reelsid, Long userid) throws Exception {

        Reels reels= reelsRepository.findById(reelsid).orElseThrow(()->new ResourceNotFoundException("Reels not found","id",reelsid));

        if(reels.getUser().getId()==userid)
        {
            reelsRepository.delete(reels);
            return true;
        }
        return false;
    }

    @Override
    public List<Reels> getReelsByUser(Long userid) throws Exception {

        User user= userservice.finduserbyid(userid);

        if(user==null)
        {
            throw new ResourceNotFoundException("User not found","id",userid);
        }

        return reelsRepository.findByUser(user);

    }

    @Override
    public List<Reels> getAllReels() throws Exception {

        return reelsRepository.findAll();
    }

    @Override
    public Reels likeReels(Long reelsid, Long userid) throws Exception {

        Reels reels= reelsRepository.findById(reelsid).orElseThrow(()->new ResourceNotFoundException("Reels not found","id",reelsid));

        User user= userservice.finduserbyid(userid);

        if(reels.getLikes().contains(user))
        {
            reels.getLikes().remove(user);
        }
        else{
            reels.getLikes().add(user);
        }

        return reelsRepository.save(reels);
    }
}
