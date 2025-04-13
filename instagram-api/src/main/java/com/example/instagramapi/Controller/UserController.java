package com.example.instagramapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.User;
import com.example.instagramapi.Service.UserService;

@RestController
@RequestMapping("/api/Instausers")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<User>registerUser(@RequestBody	User user){
		
		User registerUser = userService.registerUser(user);
		return new ResponseEntity<User>(registerUser,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/findUserById/{userId}")
	public ResponseEntity<User>findUserById(@PathVariable	Integer userId){
		
		User userById = userService.findUserById(userId);
		return new ResponseEntity<User>(userById,HttpStatus.CREATED);
	}
	

	@GetMapping("/followUser/{reqUserId}/{followUserId}")
	public ResponseEntity<String>followUser(@PathVariable Integer reqUserId,@PathVariable  Integer followUserId ){
		
		String followUser = userService.followUser(reqUserId, followUserId);
		return new ResponseEntity<String>(followUser,HttpStatus.OK);
	}
	
	
	@GetMapping("/unfollowUser/{reqUserId}/{followUserId}")
	public ResponseEntity<String>unfollowUser(@PathVariable Integer reqUserId,@PathVariable  Integer followUserId ){
		
		String followUser = userService.unfollowUser(reqUserId, followUserId);
		return new ResponseEntity<String>(followUser,HttpStatus.OK);
	}
	
	
	@GetMapping("/byIds")
    public ResponseEntity<List<User>> getUsersByIds(@RequestParam List<Integer> userIds) {
        try {
            List<User> users = userService.findUserByIds(userIds);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UserException e) {
            // Handle the exception, return an appropriate response
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
	}

	@GetMapping("/searchUser/{query}")
    public ResponseEntity<List<User>> searchUser(@PathVariable String query) {
        
            List<User> searchUser = userService.searchUser(query);
            return new ResponseEntity<>(searchUser, HttpStatus.OK);
        } 
	
	
	
	@PutMapping("/updateUserDetails/{id}")
    public ResponseEntity<User> updatedUserDetails( 
    	 @RequestBody	User updatedUser, 
    	@PathVariable (name ="id")Integer Id
    		) { 
        
             // User existingUser;
			User updatedUserDetails = userService.updatedUserDetails(updatedUser, Id);
            return new ResponseEntity<User>(updatedUserDetails, HttpStatus.OK);
        
	}
	
	

	@DeleteMapping("/deleteUserDeatils/{id}")
    public ResponseEntity<String> deleteUserDeatils(@PathVariable Integer id) {
        String response = userService.deleteUserDeatils(id);
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
	
	
	@PostMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateUser(@PathVariable Integer id) {
        String response = userService.deactivateUserDeatils(id);
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
	
	
	@PostMapping("/reactivateUser/{id}")
    public ResponseEntity<String> reactivateUser(@PathVariable Integer id) {
    
		String reactivateUser = userService.reactivateUser(id);
        return new ResponseEntity<String>(reactivateUser,HttpStatus.OK);
    }
	
	@PostMapping("/blockUser/{reqUserId}/{blockUserId}")
	public ResponseEntity<String> blockUser(@PathVariable Integer reqUserId, @PathVariable Integer blockUserId) throws UserException {
	    try {
	      String message = userService.blockUser(reqUserId, blockUserId);
	      return ResponseEntity.ok(message);
	    } catch (UserException e) {
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	  }
	
	
	
	@PostMapping("/unblockUser/{reqUserId}/{blockUserId}")
	public ResponseEntity<String> unblockUser(@PathVariable Integer reqUserId, @PathVariable Integer blockUserId) throws UserException {
	    try {
	      String message = userService.unBlockUser(reqUserId, blockUserId);
	      return ResponseEntity.ok(message);
	    } catch (UserException e) {
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	  }
	
	
	
	
	
}
	
	
	
	