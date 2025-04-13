package com.example.instagramapi.Model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.instagramapi.DTO.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_comments")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Embedded
	@AttributeOverrides({ 
	@AttributeOverride(column = @Column(name="user_id"), name = "id") ,
	@AttributeOverride(column = @Column(name="user_email"), name = "email") 		
	
	})
	private UserDto user;
	
	private String content;
	
	@Embedded
	@ElementCollection
	private Set<UserDto> likedByUsers= new HashSet<>();
	
	
	private LocalDateTime createdAt;
	
	private  int likeCount;

	

	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public UserDto getUser() {
		return user;
	}


	public void setUser(UserDto user) {
		this.user = user;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Set<UserDto> getLikedByUsers() {
		return likedByUsers;
	}


	public void setLikedByUsers(Set<UserDto> likedByUsers) {
		this.likedByUsers = likedByUsers;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
