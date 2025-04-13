package com.example.instagramapi.Service;

import java.util.List;

import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.User;

public interface UserService {

	public User registerUser(User user)  throws UserException;
	
	public User findUserById(Integer userId)  throws UserException;
	
	public User findUserProfile(String token)  throws UserException;
	
	public User findUserByUsername(String Username)  throws UserException;
	
	public String followUser(Integer reqUserId,Integer followUserId )  throws UserException;
	
	public String unfollowUser(Integer reqUserId,Integer followUserId )  throws UserException;

	public List<User> findUserByIds(List<Integer>userIds) throws UserException;

	public List<User> searchUser(String query) throws UserException;
	
	public User  updatedUserDetails(User updatedUser,Integer id) throws UserException;

	public String deleteUserDeatils(Integer id);
	
	public String deactivateUserDeatils(Integer id);

	public String reactivateUser(Integer id);
	
	public String blockUser(Integer reqId,Integer blockUserId) throws UserException;
	
	public String unBlockUser(Integer reqId,Integer blockUserId) throws UserException;
	
	
}
