package com.example.instagramapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagramapi.DTO.CustomResponsePost;
import com.example.instagramapi.Exceptions.PostException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Post;
import com.example.instagramapi.Model.User;
import com.example.instagramapi.Service.PostService;
import com.example.instagramapi.Service.UserService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
//public Post createPost(Post post,Integer userId) throws UserException;
	
	@PostMapping("/createPost/{userId}")
	public ResponseEntity<Post>createPost(@RequestBody	Post post, 
			@PathVariable  Integer userId  ){
		Post post2 = postService.createPost(post, userId);
		return new ResponseEntity<Post>(post2,HttpStatus.CREATED);
	}
	
	//All User Post WIll be Reteurn here
	//	public List<Post> findpostByUserId(Integer userId)throws UserException;
	
	
	@GetMapping("/findpostByUserId/{userId}")
	public ResponseEntity< List<Post>>findpostByUserId(@PathVariable  Integer userId  ){
		 List<Post> findpostByUserId = postService.findpostByUserId(userId);
		return new ResponseEntity<List<Post> >(findpostByUserId,HttpStatus.OK);
	}
	
	//	public String deletePost(Integer postId,Integer userId) throws UserException,PostException;

	@DeleteMapping("/deletePost/{userId}/{postId}")
	public ResponseEntity<String>deletePost(@PathVariable  Integer userId,
			@PathVariable	Integer postId) throws UserException, PostException{
		
		String deletePost = postService.deletePost(postId, userId);
		return new ResponseEntity<String>(deletePost,HttpStatus.OK);	
	}

	
	//public Post findpostById(Integer postId) throws PostException {

	@GetMapping("/findpostById/{postId}")
	public ResponseEntity<Post>findpostById(@PathVariable  Integer postId  ) throws PostException{
		  Post findpostById = postService.findpostById(postId);
		return new ResponseEntity<Post>(findpostById,HttpStatus.OK);
	}
	
	
	//	public List<Post> findpostByUserIds(List<Integer> userIds) throws PostException {

	@GetMapping("/findpostByUserIds/")
	public ResponseEntity< List<Post>>findpostByUserIds(@RequestParam List<Integer> userIds ) throws PostException{
		 List<Post> findpostByUserIds = postService.findpostByUserIds(userIds);
		return new ResponseEntity<List<Post> >(findpostByUserIds,HttpStatus.OK);
	}
	
	//	public String savedPosts(Integer postId,Integer userId) throws PostException,UserException;
	
	 @PostMapping("/save/{postId}/{userId}")
	    public ResponseEntity<String> savePost(@PathVariable Integer postId, @PathVariable Integer userId) {
	        try {
	            String result = postService.savedPosts(postId, userId);
	            return new ResponseEntity<>(result, HttpStatus.OK);
	        } catch (PostException | UserException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }
	 
	 @PostMapping("/unsavePost/{postId}/{userId}")
	    public ResponseEntity<String> unsavePost(@PathVariable Integer postId, @PathVariable Integer userId) {
	        try {
	            String result = postService.unsavedPosts(postId, userId);
	            return new ResponseEntity<>(result, HttpStatus.OK);
	        } catch (PostException | UserException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }
	 
	 //Sendiong Custom Excdeption SO Created DTO for it 
// @PostMapping("/like/{postId}/{userId}")
//	    public ResponseEntity<Post> likePost(@PathVariable Integer postId, @PathVariable Integer userId) {
//	        try {
//	            Post post = postService.likedPost(postId, userId);
//	            return new ResponseEntity<>(post, HttpStatus.OK);
//	        } catch (PostException | UserException e) {
//	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//	        }
//	    }

	 //Sendiong Custom Excdeption SO Created DTO for it 

//	    @PostMapping("/unlike/{postId}/{userId}")
//	    public ResponseEntity<Post> unlikePost(@PathVariable Integer postId, @PathVariable Integer userId) {
//	        try {
//	            Post post = postService.unlikedPost(postId, userId);
//	            return new ResponseEntity<>(post, HttpStatus.OK);
//	        } catch (PostException | UserException e) {
//	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//	        }
//	    }
	
	    @PostMapping("/like/{postId}/{userId}")
	    public ResponseEntity<CustomResponsePost> likePost1(@PathVariable Integer postId, @PathVariable Integer userId) {
	        try {
	            Post post = postService.likedPost(postId, userId);
	            return new ResponseEntity<>(new CustomResponsePost(post, "Post liked successfully"), HttpStatus.OK);
	        } catch (PostException | UserException e) {
	            return new ResponseEntity<>(new CustomResponsePost(null, e.getMessage()), HttpStatus.BAD_REQUEST);
	        }
	    }
	
	    @PostMapping("/unlike/{postId}/{userId}")
	    public ResponseEntity<CustomResponsePost> unlikePost(@PathVariable Integer postId, @PathVariable Integer userId) {
	        try {
	            Post post = postService.unlikedPost(postId, userId);
	            return new ResponseEntity<>(new CustomResponsePost(post, "Post liked successfully"), HttpStatus.OK);
	        } catch (PostException | UserException e) {
	            return new ResponseEntity<>(new CustomResponsePost(null, e.getMessage()), HttpStatus.BAD_REQUEST);
	        }
	    }
	
	
	//	public Post updatePost(Post post, Integer userId) throws UserException, PostException {
	    
	    
	    @PostMapping("/updatePost/{userId}")
		public ResponseEntity<Post>updatePost(@RequestBody	Post post, 
				@PathVariable  Integer userId  ) throws UserException, PostException {
	   	 Post updatePost = postService.updatePost(post, userId);
	 			return new ResponseEntity<Post>(updatePost,HttpStatus.ACCEPTED);	
	    	
			//return null;
			
		} 
}
