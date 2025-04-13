package com.example.instagramapi.Service;

import java.util.List;

import com.example.instagramapi.DTO.UserDto;
import com.example.instagramapi.Exceptions.StoryException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Story;

public interface StoryService {

	public Story createSStory(Integer userId,Story story) throws UserException;
	
	 public Story updateStory(Integer userId,Story story)  throws StoryException,UserException;

	 public String deleteStory(Integer userId,Integer storyId) throws StoryException;

	 public List<Story> findallStoryofUserid(Integer userId)  throws UserException,StoryException;
	 
	 //public String SavedStory(Integer userid, Integer Storyid) throws StoryException;
	 
	 public String ClosedFriendsStoried(Integer userId, String content,List<UserDto>allowedUsers)throws StoryException;

	 

}
