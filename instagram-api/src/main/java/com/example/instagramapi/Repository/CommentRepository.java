package com.example.instagramapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.instagramapi.Model.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer> {

	
	
}
