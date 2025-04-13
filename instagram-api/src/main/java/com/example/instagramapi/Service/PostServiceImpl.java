package com.example.instagramapi.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagramapi.DTO.UserDto;
import com.example.instagramapi.Exceptions.PostException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Post;
import com.example.instagramapi.Model.User;
import com.example.instagramapi.Repository.PostRepository;
import com.example.instagramapi.Repository.UserRepository;

@Service
public class PostServiceImpl  implements PostService{

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;

	@Override
	public Post createPost(Post post,Integer userId) throws UserException {

		User userById = userService.findUserById(userId);

		UserDto userDto = new UserDto();
		userDto.setEmail(userById.getEmail());
		userDto.setId(userById.getId());
		userDto.setName(userById.getName());
		userDto.setUsername(userById.getUsername());
		userDto.setUserImage(userById.getImage());
	
		post.setUser(userDto);
		Post createdPost = postRepo.save(post);
		return createdPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws UserException, PostException {

		Post post = findpostById(postId);
		User userById = userService.findUserById(userId);
	
		if(post.getUser().getId().equals(userById.getId())) {
			postRepo.deleteById(post.getId());
			return "Post Deleted SUccesfully";
		}
		throw new PostException("You cant delete Other User Posts");
	}

	@Override
	public List<Post> findpostByUserId(Integer userId) throws UserException {

		List<Post> allPostsOfUsers = postRepo.findByUserId(userId);
		
		if(allPostsOfUsers.size()==0) {
			throw new UserException("This user has Not posted anything");
		}

		return allPostsOfUsers;
	}

	@Override
	public Post findpostById(Integer postId) throws PostException {

		 Post orElseThrow = postRepo.findById(postId).orElseThrow( () -> new UserException("Post Not Found with id " +postId)); 
		return orElseThrow;
	}

	@Override
	public List<Post> findpostByUserIds(List<Integer> userIds) throws PostException {

		List<Post> allPostsUserIds = postRepo.findAllPostsUserIds(userIds);
		
		if(allPostsUserIds.size()==0) {
			throw new PostException("No Post Avaiable");
		}
		return allPostsUserIds;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public String savedPosts(Integer postId, Integer userId) throws PostException, UserException {

		//Get user and Post Deatils
		Post post = findpostById(postId);
		 User userById = userService.findUserById(userId);
		
		 //Logic to check if User is follower or not not to allow Saving of post	
		 UserDto postOwner = post.getUser();
		 User postOwnerEntity = userService.findUserById(postOwner.getId());
		 
		 boolean isFollwer=false; 
		 for (UserDto singleFollower :postOwnerEntity.getFollower()) {
			 if (singleFollower != null && singleFollower.getUsername().equals(userById.getUsername())) {
				 isFollwer=true;		 
			 } 
		 }
		 
		 if(!isFollwer) {
	       throw new UserException("Only followers can Save the post.");
		 }
		//Check the User present or not
		if(!userById.getSavePost().contains(post)) {
			userById.getSavePost().add(post);
			userRepo.save(userById);
			return "Post Saved  Succesfully";

		}
		return "Post Already Saved ";
	}

	@Override
	public String unsavedPosts(Integer postId, Integer userId) throws PostException, UserException {

		//Get user and Post Deatils
		Post post = findpostById(postId);
		 User userById = userService.findUserById(userId);
		 
		 //Logic to check if User is follower or not not to allow Saving of post	
		 UserDto postOwner = post.getUser();
		 User postOwnerEntity = userService.findUserById(postOwner.getId());
		 
		 boolean isFollwer=false; 
		 for (UserDto singleFollower :postOwnerEntity.getFollower()) {
			 if (singleFollower != null && singleFollower.getUsername().equals(userById.getUsername())) {
				 isFollwer=true;		 
			 } 
		 }
		 
		 if(!isFollwer) {
	       throw new UserException("Only followers can Save the post.");
		 }
		 
		 
		
		//Check the User present or not
		if(userById.getSavePost().contains(post)) {
			userById.getSavePost().remove(post);
			userRepo.save(userById);
			return "Post Removed Succesfully";

		}
		return "Post Already Removed";
	}

	@Override
	public Post likedPost(Integer PostId, Integer userId) throws PostException, UserException {

		User userById = userService.findUserById(userId);
		Post postDeatils = findpostById(PostId);

		//TOdo 1: to check only follwers can Liked a Post
		
		 UserDto postOwnerDto = postDeatils.getUser(); // Assuming Post entity has a reference to UserDto

	        // Convert UserDto to User entity to check followers
	        User postOwner = userService.findUserById(postOwnerDto.getId());

	        // Convert UserDto to User entity to check followers
	       // User postOwner = userService.findUserById(postOwnerDto.getId());

	        // Check if the user is a follower of the post owner (Traditional way)
	        boolean isFollower = false;
	        for (UserDto follower : postOwner.getFollower() ) {
	            if (follower != null && follower.getUsername().equals(userById.getUsername())) {
	            //if (follower.getId().equals(userById.getId())) {
	                isFollower = true;
	                break;
	            }
	        }
	        
	        if (!isFollower) {
	            throw new UserException("Only followers can like the post.");
	        }
	        
	        
		UserDto user = new UserDto();
		user.setEmail(userById.getEmail());
		user.setId(userById.getId());
		user.setName(userById.getName());
		user.setUsername(userById.getUsername());
		user.setUserImage(userById.getImage());
		
//		postDeatils.getLikedByUsers().add(user);
//		Post save = postRepo.save(postDeatils);
		
		 // Checking  if user has already liked the post
	    if (!postDeatils.getLikedByUsers().contains(user)) {
	        // Add user to the list of users who liked the post
	    	postDeatils.getLikedByUsers().add(user);
	        
	        // Incrementing the like count
	    	postDeatils.setLikeCount(postDeatils.getLikeCount() + 1);
	        
	        // Save the updated post details
	        postRepo.save(postDeatils);
	        sendLikeNotification(postOwner, userById, postDeatils);

	        
	    } else {
	        throw new UserException("User has already liked this post.");
	    }
		return postDeatils;
	}

	@Override
	public Post unlikedPost(Integer PostId, Integer userId) throws PostException, UserException {
			User userById = userService.findUserById(userId);
			Post postDeatils = findpostById(PostId);
			
			//TOdo 1: to check only follwers can Liked a Post
			 UserDto postOwnerDto = postDeatils.getUser(); // Assuming Post entity has a reference to UserDto

		        // Convert UserDto to User entity to check followers
		        User postOwner = userService.findUserById(postOwnerDto.getId());

		        // Convert UserDto to User entity to check followers
		       // User postOwner = userService.findUserById(postOwnerDto.getId());

		        // Check if the user is a follower of the post owner (Traditional way)
		        boolean isFollower = false;
		        for (UserDto follower : postOwner.getFollower() ) {
		            if (follower != null && follower.getUsername().equals(userById.getUsername())) {
		            //if (follower.getId().equals(userById.getId())) {
		                isFollower = true;
		                break;
		            }
		        }
		        
		        if (!isFollower) {
		            throw new UserException("First Become Follwer to Unlike the Post");
		        }
			UserDto user = new UserDto();
			user.setEmail(userById.getEmail());
			user.setId(userById.getId());
			user.setName(userById.getName());
			user.setUsername(userById.getUsername());
			user.setUserImage(userById.getImage());

			 // Check if the user has liked the post
		    if (postDeatils.getLikedByUsers().contains(user)) {
		        // Remove user from the list of users who liked the post
		    	postDeatils.getLikedByUsers().remove(user);
		        
		        // Decrement the like count
		    	postDeatils.setLikeCount(postDeatils.getLikeCount() - 1);
		        
		        // Save the updated post details
		        Post savedPost = postRepo.save(postDeatils);
		        sendunLikeNotification(postOwner, userById, postDeatils);

		        return savedPost;
		    } else {
		        throw new UserException("First Liked Post,Then Unliked Post" );
		    }
		}

	@Override
	public Post updatePost(Post post, Integer userId) throws UserException, PostException {

		User userById = userService.findUserById(userId);

	    Post existingPost = postRepo.findById(post.getId()).orElseThrow( () -> new PostException("Post Not Found"));

		if(!existingPost.getUser().getUsername().equals(userById.getUsername())) {
			throw new PostException("You are not owner of post");
		}
		
		
//		for( Post singlePost: byUserId ) {
//			if( singlePost.getUser().equals(post.getUser()) ) {
//				throw new PostException("You are not owner of post");
//			}
//				
//		}	

	     if(post.getCaption()!=null) { 
	    	 existingPost.setCaption(post.getCaption());
	    }
	     
	     if(post.getImage()!=null) { 
	    	 existingPost.setImage(post.getImage());
	    }
	     
	     if(post.getLocation()!=null) { 
	    	 existingPost.setLocation(post.getLocation());
	    }
		Post updatedPostResponse = postRepo.save(existingPost);
		
		return updatedPostResponse;
	}
	
	
	private void sendLikeNotification(User postOwner, User liker, Post post) {
	    // Implementation of sending notification
	    // You can use any notification service or simply print a message for now
	    System.out.println("Notification: Hi "+ post.getUser().getUsername() + "Your post has been LikedBy: "  + liker.getUsername() + "  ,your post with ID: " + post.getId()+ "At time "+ LocalDateTime.now());
	}
	
	private void sendunLikeNotification(User postOwner, User liker, Post post) {
	    // Implementation of sending notification
	    // You can use any notification service or simply print a message for now
	    System.out.println("Notification: Hi "+ post.getUser().getUsername() + "Your post has been unLikedBy: "  + liker.getUsername() + " ,your post is ID: " + post.getId()+ "At time "+ LocalDateTime.now());
	}
	
}
