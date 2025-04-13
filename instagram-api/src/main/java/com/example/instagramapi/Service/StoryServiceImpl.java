package com.example.instagramapi.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagramapi.DTO.UserDto;
import com.example.instagramapi.Exceptions.StoryException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Story;
import com.example.instagramapi.Model.User;
import com.example.instagramapi.Repository.CommentRepository;
import com.example.instagramapi.Repository.PostRepository;
import com.example.instagramapi.Repository.StoryRepository;
import com.example.instagramapi.Repository.UserRepository;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private StoryRepository storyRepo;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	
	
	@Override
	public Story createSStory(Integer userId, Story story) throws UserException {

		User userById = userService.findUserById(userId);
		
		UserDto userDto = new UserDto();
		userDto.setEmail(userById.getEmail());
		userDto.setId(userById.getId());
		userDto.setName(userById.getName());
		userDto.setUsername(userById.getUsername());
		userDto.setUserImage(userById.getImage());
	
		story.setUser(userDto);
		story.setTimestamp(LocalDateTime.now());
		
		//now add story to user 
		userById.getStories().add(story);
		
		Story save = storyRepo.save(story);
		return save;
	}

	@Override
	public Story updateStory(Integer userId, Story story) throws StoryException,UserException {

		User userById = userService.findUserById(userId);
		Story existingstoryEntity = storyRepo.findById(story.getId()).orElseThrow( () -> new StoryException( " Story Not found"));
		
		if(!existingstoryEntity.getUser().getUsername().equals(userById.getUsername())) {
			throw new StoryException("You cant update Others Story,As You are Not Owner");
		}
		
		if(story.getCaption()!=null) {
			existingstoryEntity.setCaption(story.getCaption());
		}
		
		if(story.getImage()!=null) {
			existingstoryEntity.setImage(story.getImage());
		}
		Story updatedStorySave = storyRepo.save(existingstoryEntity);
		return updatedStorySave;
	}

	@Override
	public String deleteStory(Integer userId,Integer storyId ) throws StoryException {

		User userById = userService.findUserById(userId);

		Story existingstoryEntity = storyRepo.findById(storyId).orElseThrow( () ->  new StoryException("Story not found"));
		
	if(!existingstoryEntity.getUser().getUsername().equals(userById.getUsername())) {
			throw new StoryException("You cant Deleted Others Story,As You are Not Owner");
	}
	
	storyRepo.delete(existingstoryEntity);
	return "Story Deletdd";
	
	}

	@Override
	public List<Story> findallStoryofUserid(Integer userId) throws UserException, StoryException {

		User userById = userService.findUserById(userId);
		List<Story> stories = userById.getStories();
		
		if (stories.size()==0) {
		 throw new StoryException("No STories Present of User");
		}
		
		return stories;
	}

	@Override
	public String ClosedFriendsStoried(Integer userId, String content,List<UserDto>allowedUsers) throws StoryException {

		User owner = userService.findUserById(userId);
		UserDto userDto= new UserDto();
		BeanUtils.copyProperties(owner, userDto);
		
		
		Story story = new Story();
		story.setCaption(content);
		story.setUser(userDto);
		story.setTimestamp(LocalDateTime.now());
		
		
		  for (UserDto singleUser:allowedUsers) { 
			  if(owner.getFollower().contains(singleUser.getUsername())) {
				  allowedUsers.add(userDto);
				  
			  }	  
		  }
		
		
		
		
		
		return null;
	}

//	@Override
//	public String SavedStory(Integer userid, Integer Storyid) throws StoryException {
//
//		User userById = userService.findUserById(userid);
//
//		Story existingstoryEntity = storyRepo.findById(Storyid).orElseThrow( () ->  new StoryException("Story not found"));
//		
//		//Logic 1 : to check the User is creator of story
//		
//		if(existingstoryEntity.getUser().getUsername().equals(userById.getUsername())) {
//		
//			if(!userById.getSaveStory().contains(existingstoryEntity)) {
//				userById.getStories().add(existingstoryEntity);
//				userRepo.save(userById);
//				return "Story Saved";
//			}else			{
//				return "Story already Saved,You can Only save One Time";
//			}
//		}
//		return "Others users cant save Story";
//	}
//	
	
	
	

}
