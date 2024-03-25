package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Reels;

import java.util.List;

public interface ReelsService {


    public Reels createReels(Reels reels, Long userid) throws Exception;

    public Reels getReelsById(Long reelsid) throws Exception;

    public Boolean deleteReels(Long reelsid, Long userid) throws Exception;

    public List<Reels> getReelsByUser(Long userid) throws Exception;

    public List<Reels> getAllReels() throws Exception;

    public Reels likeReels(Long reelsid, Long userid) throws Exception;




}
