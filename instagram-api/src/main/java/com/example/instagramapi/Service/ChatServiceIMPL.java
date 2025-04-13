package com.example.instagramapi.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagramapi.Model.Chat;
import com.example.instagramapi.Model.User;
import com.example.instagramapi.Repository.ChatRepository;

@Service
public class ChatServiceIMPL implements ChatService {

	@Autowired
	private ChatRepository chatRepo;
	
	
	@Override
	public Chat createChat(User reqUser, User user2) {

        Chat isExists = chatRepo.findChatByUsersId(reqUser.getId(), user2.getId());
		
		
		if(isExists!=null) {
			return isExists;
		}
		
		Chat newChat= new Chat();
		newChat.getUsers().add(user2);
		newChat.getUsers().add(reqUser);
		newChat.setTimestamp(LocalDateTime.now());
		
		Chat save = chatRepo.save(newChat);
		return save;
	}

	@Override
	public Chat findByById(Integer chatId) throws Exception {

		Chat orElseThrow = chatRepo.findById(chatId).orElseThrow(  () -> new Exception( "Chat not found"));
		return orElseThrow;
	}

	@Override
	public List<Chat> findUsersChat(Integer Userid) {

		List<Chat> byUsersId = chatRepo.findByUsersId(Userid);
		
		return byUsersId;
	}

}
