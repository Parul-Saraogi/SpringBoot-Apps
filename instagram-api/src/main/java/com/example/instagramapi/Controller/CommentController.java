package com.example.instagramapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagramapi.Exceptions.CommentException;
import com.example.instagramapi.Exceptions.PostException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Comments;
import com.example.instagramapi.Model.Post;
import com.example.instagramapi.Service.CommentsService;
import com.example.instagramapi.Service.PostService;
import com.example.instagramapi.Service.UserService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
@Autowired
private UserService userService;

@Autowired
private PostService postService;

@Autowired
private CommentsService commentService;


//public Comments createComment(Comments passComment,Integer postId , Integer userId) throws UserException,PostException;

	@PostMapping("/createComment/{userId}/{postId}")
	public ResponseEntity<Comments>createComment(@RequestBody Comments passComment, 
		@PathVariable  Integer postId,
		@PathVariable Integer userId) throws UserException, PostException{
	
		Comments comment = commentService.createComment(passComment, postId, userId);
		return new ResponseEntity<Comments>(comment,HttpStatus.CREATED);
			}

	//public Comments findCommentById(Integer commentId) throws CommentException {

	@GetMapping("/findCommentById/{commentId}")
	public ResponseEntity<Comments>findCommentById(
		@PathVariable Integer commentId) throws UserException, PostException, CommentException{
	
		Comments commentById = commentService.findCommentById(commentId);
		return new ResponseEntity<Comments>(commentById,HttpStatus.OK);
	}
	
	//	public Comments likeComments(Integer commentId, Integer userId) throws CommentException, UserException, PostException {

	@PostMapping("/likeComments/{userId}/{commentId}")
	public ResponseEntity<Comments>likeComments( @PathVariable Integer userId,
		@PathVariable Integer commentId) throws UserException, PostException, CommentException{
	
		 Comments likeComments = commentService.likeComments(commentId, userId);
		return new ResponseEntity<Comments>(likeComments,HttpStatus.ACCEPTED);
	}
	
	//	public Comments unlikeComments(Integer commentId, Integer userId) throws CommentException, UserException {

	
	@PostMapping("/unlikeComments/{userId}/{commentId}")
	public ResponseEntity<Comments>unlikeComments( @PathVariable Integer userId,
		@PathVariable Integer commentId) throws UserException, PostException, CommentException{
	
		 Comments likeComments = commentService.unlikeComments(commentId, userId);
		return new ResponseEntity<Comments>(likeComments,HttpStatus.ACCEPTED);
	}
	
	//	public String deleteComments(Integer commentId, Integer userId, Integer postId)

	@DeleteMapping("/deleteComments/{userId}/{commentId}")
	public ResponseEntity<String>deleteComments( @PathVariable Integer userId,
		@PathVariable Integer commentId)
				throws UserException, PostException, CommentException
	{
		 String deleteComments = commentService.deleteComments(commentId, userId);
		return new ResponseEntity<String>(deleteComments,HttpStatus.OK);
	}
	
		








	
	
}
