package com.example.instagramapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.instagramapi.Model.Chat;
import com.example.instagramapi.Model.User;

public interface ChatRepository  extends JpaRepository<Chat, Integer>{

	public List<Chat> findByUsersId(Integer userId);
	
	//Query to check is chat already established or not
	
//	@Query("select c from Chat c Where c.")
//	public Chat findChatByUsersId(@Param("user")   User user, @Param(value = "reqUser") User reqUser);

	 @Query("SELECT c FROM Chat c JOIN c.users u1 JOIN c.users u2 WHERE u1.id = :userId1 AND u2.id = :userId2")
	 public    Chat findChatByUsersId(@Param("userId1") Integer integer, @Param("userId2") Integer integer2);
	
}
