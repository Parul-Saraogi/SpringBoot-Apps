package com.example.instagramapi.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagramapi.DTO.UserDto;
import com.example.instagramapi.Exceptions.CommentException;
import com.example.instagramapi.Exceptions.PostException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Comments;
import com.example.instagramapi.Model.Post;
import com.example.instagramapi.Model.User;
import com.example.instagramapi.Repository.CommentRepository;
import com.example.instagramapi.Repository.PostRepository;
import com.example.instagramapi.Repository.UserRepository;

@Service
public class CommentsServiceIMPL implements CommentsService {

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
	
	
	@SuppressWarnings("unused")
	@Override
	public Comments createComment(Comments passComment, Integer postId, Integer userId)
			throws UserException, PostException {

		User userById = userService.findUserById(userId);
		Post postDeatils = postService.findpostById(postId);
		
		//Foe Comment we need User Dto as this will tell which user created this commen nt
		UserDto userDto= new UserDto();
		userDto.setEmail(userById.getEmail());
		userDto.setId(userById.getId());
		userDto.setName(userById.getName());
		userDto.setUsername(userById.getUsername());
		userDto.setUserImage(userById.getImage());
		
		//Now we will set this user into Comment
		passComment.setUser(userDto);
		passComment.setCreatedAt(LocalDateTime.now());
		
		//Saving Comment InDB
	    Comments saveComment = commentRepo.save(passComment);
	    
	    //Now adding COmment into Post and Saving Post
	    postDeatils.getComments().add(saveComment);
	    postRepo.save(postDeatils);
	    
		return saveComment;
	}

	@Override
	public Comments findCommentById(Integer commentId) throws CommentException {

		
		 Comments  findCommentbyId= commentRepo.findById(commentId).orElseThrow( () -> new CommentException("Comment Not Found for this post"));   
		return findCommentbyId;
	}

	@Override
	public Comments likeComments(Integer commentId, Integer userId) throws CommentException, UserException, PostException {

		//The User who will like the comment
		User userById = userService.findUserById(userId);	
		 //findCommentById use to find the comment
		 Comments commentById = findCommentById(commentId);
		 
		//Foe Comment we need User Dto as this will tell which user created this commen nt
		UserDto userDto= new UserDto();
		userDto.setEmail(userById.getEmail());
		userDto.setId(userById.getId());
		userDto.setName(userById.getName());
		userDto.setUsername(userById.getUsername());
		userDto.setUserImage(userById.getImage());
		
		//Checking User already Liked the Comment
		if(!commentById.getLikedByUsers().contains(userDto)) {
			commentById.getLikedByUsers().add(userDto);
			commentById.setLikeCount(commentById.getLikeCount() + 1 );
			
			//Adding User like to comment
			commentById.getLikedByUsers().add(userDto);
			Comments saveLiked = commentRepo.save(commentById);
			return saveLiked;
		}else
		{
			throw new CommentException("User already Liked the Comment");
		}
	}

	@Override
	public Comments unlikeComments(Integer commentId, Integer userId) throws CommentException, UserException {

		//The User who will like the comment
				User userById = userService.findUserById(userId);
				
				// Post findpostById = postService.findpostById(userById.getId());
				 	
				 //findCommentById use to find the comment
				 
				 Comments commentById = findCommentById(commentId);
				//Foe Comment we need User Dto as this will tell which user created this commen nt
				UserDto userDto= new UserDto();
				userDto.setEmail(userById.getEmail());
				userDto.setId(userById.getId());
				userDto.setName(userById.getName());
				userDto.setUsername(userById.getUsername());
				userDto.setUserImage(userById.getImage());
				
			
				
				if(commentById.getLikedByUsers().contains(userDto)) {
					
				}
				
				
				
				//Adding User like to comment
				commentById.getLikedByUsers().remove(userDto);
				Comments saveLiked = commentRepo.save(commentById);
				return saveLiked;
	}

	
	
	
	@Override
	public String deleteComments(Integer commentId, Integer userId)
			throws CommentException, UserException {

		//Logic 1 Only Users Who create comments should delte it 
		
		User userById = userService.findUserById(userId);
		Comments commentById = findCommentById(commentId);

		   if (commentById.getUser().getUsername().equals(userById.getUsername())) {
	            // Remove the comment from the associated post's comment list
	            Post associatedPost = findPostByCommentId(commentId); // Method to find the post containing this comment
	            if (associatedPost != null) {
	                associatedPost.getComments().remove(commentById);
	                postRepo.save(associatedPost); // Save the post to update its comments
	            }

	            commentRepo.deleteById(commentById.getId());
	            return "Comment Deleted";
	        }   
	        return "You cant delete  Other User Comments " + userById.getUsername();
	    }
	
	private Post findPostByCommentId(Integer commentId) {
        return postRepo.findByCommentId(commentId);
    }

	
}
