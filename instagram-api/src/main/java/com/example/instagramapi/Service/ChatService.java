package com.example.instagramapi.Service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.instagramapi.Model.Chat;
import com.example.instagramapi.Model.User;

public interface ChatService {

	public Chat createChat(User reqUser, User user2);
	
	public Chat findByById(Integer chatId)  throws Exception;
	
	public List<Chat> findUsersChat(Integer Userid);
	
	
}
