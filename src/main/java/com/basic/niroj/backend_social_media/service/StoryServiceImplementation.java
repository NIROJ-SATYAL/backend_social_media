package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Exception.ResourceNotFoundException;
import com.basic.niroj.backend_social_media.Exception.ResponseException;
import com.basic.niroj.backend_social_media.Model.Story;
import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.StoryRepository;
import com.basic.niroj.backend_social_media.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImplementation implements  StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private Userservice userservice;
    @Override
    public Story createStory(Story story, Long userid) throws Exception {

        try
        {

            User user = userservice.finduserbyid(userid);

            if(user.getId()==userid)
            {
                story.setUser(user);
                return storyRepository.save(story);
            }
            else {
                throw new ResourceNotFoundException("Invalid Access","id",userid);
            }
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("Story not created");
        }
    }

    @Override
    public Story getStoryById(Long storyid) throws Exception {

        try
        {
            return storyRepository.findById(storyid).get();
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Story not found","id",storyid);
        }
    }

    @Override
    public Boolean deleteStory(Long storyid, Long userid) throws Exception {


        Story story = storyRepository.findById(storyid).orElseThrow(()->new ResourceNotFoundException("Story not found","id",storyid));


        if(story.getUser().getId()==userid)
        {
            storyRepository.deleteById(storyid);
            return true;
        }
        else {
            throw new ResourceNotFoundException("Invalid Access","id",userid);
        }


    }

    @Override
    public List<Story> getStoryByUser(Long userid) throws Exception {

        try
        {
            User user = userservice.finduserbyid(userid);
            return storyRepository.findByUser(user);
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("User not found","id",userid);
        }
    }

    @Override
    public List<Story> getAllStory() throws Exception {

        try
        {
            return storyRepository.findAll();
        }
        catch (Exception e)
        {
            throw  new ResponseException("No Story Found");
        }
    }

    @Override
    public Story likeStory(Long storyid, Long userid) throws Exception {

        try
        {
            Story story = storyRepository.findById(storyid).get();
            User user = userservice.finduserbyid(userid);
            List<User> likers = story.getLikedby();
            if(likers.contains(user))
            {
                likers.remove(user);
            }
            else {
                likers.add(user);
            }
            story.setLikedby(likers);
            return storyRepository.save(story);
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Story not found","id",storyid);
        }
    }

    @Override
    public Story viewStory(Long storyid, Long userid) throws Exception {


        try
        {
            Story story = storyRepository.findById(storyid).get();
            User user = userservice.finduserbyid(userid);
            List<User> viewers = story.getSeenby();
            if(!viewers.contains(user))
            {
                viewers.add(user);

                story.setSeenby(viewers);
            }


            return storyRepository.save(story);
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Story not found","id",storyid);
        }
    }

    @Override
    public List<User> getStoryViewers(Long storyid,Long userid) throws Exception {

        try
        {
            User user = userservice.finduserbyid(userid);
            Story story = storyRepository.findById(storyid).orElseThrow(()->new ResourceNotFoundException("Story not found","id",storyid));
            if(story.getUser().getId()==userid)
            {
                return story.getSeenby();
            }
            else {
                throw new ResourceNotFoundException("Invalid Access","id",userid);
            }

        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Story not found","id",storyid);
        }
    }

    @Override
    public List<User> getStoryLikers(Long userid,Long storyid) throws Exception {

        try
        {
            User user = userservice.finduserbyid(userid);
            Story story = storyRepository.findById(storyid).orElseThrow(()->new ResourceNotFoundException("Story not found","id",storyid));
            if(story.getUser().getId()==userid)
            {
                return story.getLikedby();
            }
            else {
                throw new ResourceNotFoundException("Invalid Access","id",userid);
            }

        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Story not found","id",storyid);
        }
    }
}
