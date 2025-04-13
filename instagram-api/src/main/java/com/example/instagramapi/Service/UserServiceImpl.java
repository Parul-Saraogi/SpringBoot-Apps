package com.example.instagramapi.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagramapi.DTO.UserDto;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.User;
import com.example.instagramapi.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User registerUser(User user) throws UserException {

		
		//Check with User emaik
		Optional<User> byEmailexists = userRepo.findByEmail(user.getEmail());
		
		if(byEmailexists.isPresent()) {
			throw new UserException("User alreadyPresent with this Id" + user.getEmail());
		}
		
		//Check with Username 
		Optional<User> byUsername = userRepo.findByUsername(user.getUsername());
		
		if (byUsername.isPresent()) {
			throw new UserException("User Name Taken" + user.getUsername());
			}
		
		//Checking is Null is there
		if (user.getEmail()==null || user.getPassword()==null  || user.getUsername()==null  )
		{
			throw new UserException("ALl Feilds Mandatory");
		}
		
		//Now we create the New User if all present
			User enitity = new User();
		

			BeanUtils.copyProperties(user, enitity);
			//Use setters and Getters if we are Creatin the User Roles
			enitity.setActive(true);
			userRepo.save(enitity);
			return enitity;
	}

	@Override
	public User findUserById(Integer userId) throws UserException {

		 User orElseThrow = userRepo.findById(userId).orElseThrow(() -> new UserException("User Not found with this id" + userId ));   
		 return orElseThrow;
	}

	@Override
	public User findUserProfile(String token) throws UserException {
		
		
		return null;
	}

	@Override
	public User findUserByUsername(String Username) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String followUser(Integer reqUserId, Integer followUserId) throws UserException {

//		//this will become follower
		User reqUser = findUserById(reqUserId);  

		//this will become following the Other User
		User followuser = findUserById(followUserId);


		//this will become follower
		UserDto follower= new UserDto();
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getName());
		follower.setUsername(reqUser.getUsername());
		follower.setUserImage(reqUser.getImage());
		
		//Onther user following get increase
		UserDto following= new UserDto();
		following.setEmail(followuser.getEmail());
		following.setId(followuser.getId());
		following.setUserImage(followuser.getImage());
		following.setName(followuser.getName());
		following.setUsername(followuser.getUsername());
		
		 if (reqUser.getFollowing().contains(following)) {
		        return "You are already following " + followuser.getUsername();
		    }
		
		reqUser.getFollowing().add(following);
		followuser.getFollower().add(follower);
		
		userRepo.save(followuser);
		userRepo.save(reqUser);
	
			return "You are following " +followuser.getUsername();
		}
	

	@Override
	public String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException {

				User reqUser = findUserById(reqUserId);  

				//this will become following the Other User
				User followuser = findUserById(followUserId);

				//this will become follower
				UserDto follower= new UserDto();
				follower.setEmail(reqUser.getEmail());
				follower.setId(reqUser.getId());
				follower.setName(reqUser.getName());
				follower.setUsername(reqUser.getUsername());
				follower.setUserImage(reqUser.getImage());
				
				//Onther user following get increase
				UserDto following= new UserDto();
				following.setEmail(followuser.getEmail());
				following.setId(followuser.getId());
				following.setUserImage(followuser.getImage());
				following.setName(followuser.getName());
				following.setUsername(followuser.getUsername());
				
//				reqUser.getFollowing().remove(following);
//				followuser.getFollower().remove(follower);
//				
//				userRepo.save(followuser);
//				userRepo.save(reqUser);
				
				
				// Attempt to remove the follower and following relationships
			    boolean removedFromFollowing = reqUser.getFollowing().remove(following);
			    boolean removedFromFollowers = followuser.getFollower().remove(follower);

			    // Save the changes only if both removals were successful
			    if (removedFromFollowing && removedFromFollowers) {
			        userRepo.save(followuser);
			        userRepo.save(reqUser);
			        return "You have unfollowed: " + followuser.getUsername();
			    } else {
			        throw new UserException("Failed to unfollow user. The user may not be in your following list.");
			    }
				
				
				
				
				
				
	
	//	return "You have unfollowed : " +followuser.getUsername();
	}

	@Override
	public List<User> findUserByIds(List<Integer> userIds) throws UserException {

		List<User> users = userRepo.findAllUsersByUsersIds(userIds);
		return users;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {

		List<User> usersList = userRepo.findByQuery(query);
		if(usersList.size()==0){
			throw new UserException("Use not FOund");
		}
		return usersList;

		//return null;
	}

	@Override
	public User updatedUserDetails(User updatedUser,Integer id) throws UserException {

		User existingUser = findUserById(id);
		
	   if(!existingUser.isActive()) {
			throw new UserException("You are Deactivated Cant edit Deatils :"+existingUser.getName());
		}
		
		// User existingUser = new User();
		
		if(updatedUser.getEmail()!=null) {
			existingUser.setEmail(updatedUser.getEmail());
		}
		
		if(updatedUser.getBio()!=null) {
			existingUser.setBio(updatedUser.getBio());
		}
		
		if(updatedUser.getGender()!=null) {
			existingUser.setGender(updatedUser.getGender());
		}
		

		if(updatedUser.getImage()!=null) {
			existingUser.setImage(updatedUser.getImage());
		}
		

		if(updatedUser.getMobile()!=null) {
			existingUser.setMobile(updatedUser.getMobile());
		}
		
		if(updatedUser.getPassword()!=null) {
			existingUser.setPassword(updatedUser.getPassword());
		}
		
		if(updatedUser.getName()!=null) {
			existingUser.setName(updatedUser.getName());
		}
		userRepo.save(existingUser);
		
		return updatedUser;
	}

	@Override
	public String deleteUserDeatils(Integer id) {

		User userById = findUserById(id);
		
		userRepo.delete(userById);
		
		return "You have been deleted : " + userById.getUsername() ;
	}

//    public String deactivateUserDetails(Integer id) {
//       
//    }

	@Override
	@Transactional
	public String deactivateUserDeatils(Integer id) {
			User userById = findUserById(id);
			
			if(!userById.isActive()) {
				return "YOu are already Deactivated";
			}
			
			Integer rowsUpdated =	userRepo.deactivateUserById(userById.getId());
//			//userById.set
//			userById.setActive(false);  // Update the isActive field
//	          userRepo.save(userById);  // Save the updated entity to the database

			 if (rowsUpdated > 0) {
				    // User successfully deactivated (rowsUpdated indicates affected rows)
				    return "User deactivated successfully : "+userById.getUsername();
				  } else {
				    // Handle case where user not found (no rows updated)
				    return "User with id " + id + " not found.";
				  }
			
		//	return "User deactivated successfully." + userById.getUsername();
	        
	}
	
	@Transactional
	public String reactivateUser(Integer id) {

		User userById = findUserById(id);
		
		if(userById.isActive()) {
			return "YOu are already Active First Deac tivated";
		}
		
			userById.setActive(true);
			  userRepo.save(userById);
			  return "User  Account reacitivated" + userById.getUsername();
		}

	
	private String FollowRequest(UserDto follower,UserDto follwoing,Integer reqUserId, Integer followUserId) {
		return null;	
	}

	@Override
	public String blockUser(Integer reqUserId, Integer blockUserId) throws UserException {

		User requstorUser = findUserById(reqUserId);
		User blockUser = findUserById(blockUserId);
		
		if(requstorUser.getBlockedUserIds().contains(blockUser.getId())) {
			return "User already Blocked";
		}
		for( UserDto singleFollwer   :requstorUser.getFollower()) {
			if( blockUser.getUsername().equals(singleFollwer.getUsername())  ) {
				{
					requstorUser.getBlockedUserIds().add(singleFollwer.getId());
					requstorUser.getFollower().remove(singleFollwer);
					
					userRepo.save(requstorUser);
				  return "User blocked successfully.";	
				}
		}
		}
		return "Can Block only follwers or folllwoing users";
	
	}

	@Override
	public String unBlockUser(Integer reqId, Integer blockUserId) throws UserException {

		User requstorUser = findUserById(reqId);
		User blockUser = findUserById(blockUserId);

	if(requstorUser.getBlockedUserIds().contains(blockUser.getId())){
		
		UserDto user = new UserDto();
		requstorUser.getBlockedUserIds().remove(blockUser.getId());
		BeanUtils.copyProperties(blockUser, user);
		requstorUser.getFollower().add(user);	
		userRepo.save(requstorUser);
		return "User Unblocked successfully.";	
	}
	
	return "User  Already Unblocked";
	}
}


