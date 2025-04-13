package com.example.instagramapi.Service;

import java.util.List;

import com.example.instagramapi.Exceptions.PostException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Post;
import com.example.instagramapi.Model.User;

public interface PostService {

	public Post createPost(Post post,Integer userId) throws UserException;
	
	//Taking userid So who created that post can be dleted by him only
	public String deletePost(Integer postId,Integer userId) throws UserException,PostException;

	//All User Post WIll be Reteurn here
	public List<Post> findpostByUserId(Integer userId)throws UserException;
	
	//TO get the post through  id thats why reteuning user exception if not found
	public Post findpostById(Integer postId) throws PostException;
	
	//Whewn i search one user It will give all the posts of it
	public List<Post> findpostByUserIds(List<Integer> userIds) throws PostException;

	//user will saved some Posts
	//First Will check Only Post creators Follwers or PostCreator can Sav Post
	//Then check if 
	
	
	public String savedPosts(Integer postId,Integer userId) throws PostException,UserException;
	
	public String unsavedPosts(Integer postId,Integer userId) throws PostException,UserException;

	//TO Like one post	
	public Post likedPost(Integer PostId,Integer userId) throws PostException,UserException;
	
	//TO unLike one post	
	public Post unlikedPost(Integer PostId,Integer userId) throws PostException,UserException;

	//	public User  updatedUserDetails(User updatedUser,Integer id) throws UserException;
	public Post updatePost(Post post, Integer userId) throws UserException,PostException;
	
}
