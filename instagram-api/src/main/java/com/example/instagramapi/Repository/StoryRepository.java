package com.example.instagramapi.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.instagramapi.Model.Story;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface StoryRepository extends JpaRepository<Story, Integer>{

	//@Query("select s from story s where s.user.id=: userId")
	//List<Story> findAllStoryByUserid(@Param("userId") Integer userId);
	
	 @Query("SELECT s FROM Story s WHERE s.timestamp < :cutoffTime")
	    List<Story> findStoriesOlderThan(@Param("cutoffTime") LocalDateTime cutoffTime);
	
}
