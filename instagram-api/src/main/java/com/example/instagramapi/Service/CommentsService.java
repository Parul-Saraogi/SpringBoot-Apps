package com.example.instagramapi.Service;

import com.example.instagramapi.Exceptions.CommentException;
import com.example.instagramapi.Exceptions.PostException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Comments;

public interface CommentsService {

	//Which post Realted so postId
	//Which user pass comment so taking userid
	//passComment to pass the comment
	public Comments createComment(Comments passComment,Integer postId , Integer userId) throws UserException,PostException;
	
	public Comments findCommentById(Integer commentId) throws CommentException;
	
	//Taking User id to check which user liked this Comment
	public Comments likeComments(Integer commentId, Integer userId) throws CommentException,UserException, PostException;
	
	public Comments unlikeComments(Integer commentId, Integer userId) throws CommentException,UserException;

	public String deleteComments(Integer commentId, Integer userId) throws CommentException,UserException;
}
