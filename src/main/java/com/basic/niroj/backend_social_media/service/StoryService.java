package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Story;
import com.basic.niroj.backend_social_media.Model.User;

import java.util.List;

public interface StoryService {


    public Story createStory(Story story, Long userid) throws Exception;

    public Story getStoryById(Long storyid) throws Exception;

    public Boolean deleteStory(Long storyid, Long userid) throws Exception;

    public List<Story> getStoryByUser(Long userid) throws Exception;

    public List<Story> getAllStory() throws Exception;

    public Story likeStory(Long storyid, Long userid) throws Exception;
    public Story viewStory(Long storyid, Long userid) throws Exception;

    public List<User> getStoryViewers(Long storyid) throws Exception;
    public List<User> getStoryLikers(Long storyid) throws Exception;
}
