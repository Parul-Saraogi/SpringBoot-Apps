package com.example.instagramapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagramapi.Model.Chat;
import com.example.instagramapi.Model.User;
import com.example.instagramapi.Repository.ChatRepository;
import com.example.instagramapi.Service.ChatService;
import com.example.instagramapi.Service.CommentsService;
import com.example.instagramapi.Service.PostService;
import com.example.instagramapi.Service.UserService;

@RestController
@RequestMapping("/api/Chat")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;

	
	 @PostMapping("/create/{userId1}/{userId2}")
	    public Chat createChat(@PathVariable Integer userId1, @PathVariable Integer userId2) {
	
		 User user1 = userService.findUserById(userId1); // Assuming you have a method to get user by ID
	        User user2 = userService.findUserById(userId2); 
		 
	        return chatService.createChat(user1, user2);
	    }
	//	public List<Chat> findUsersChat(Integer Userid) {

	 
	 @GetMapping("/findUsersChat/{userId1}")
	    public List<Chat> findUsersChat (@PathVariable Integer userId1) {
	
		 List<Chat> usersChat = chatService.findUsersChat(userId1);
		 
	        return usersChat;
	    }
	 
	//	public Chat findByById(Integer chatId) throws Exception {

	 
	 @GetMapping("/findByById/{userId1}")
	    public Chat findByById (@PathVariable Integer chatId) throws Exception {
	
		   Chat byById = chatService.findByById(chatId);
		 
	        return byById;
	    }
	
	
	
}
