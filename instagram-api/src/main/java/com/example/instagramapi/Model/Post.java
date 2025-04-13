package com.example.instagramapi.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String caption;
	
	private String image;
	private String location;
	private LocalDateTime createdBy;
	
	@Embedded
	@AttributeOverrides({ 
	@AttributeOverride(column = @Column(name="user_id"), name = "id") ,
	@AttributeOverride(column = @Column(name="user_email"), name = "email") 		
	
	})
	//WHo created thi story
	private UserDto user;
	
	
	@OneToMany
	private List<Comments>comments = new ArrayList<>();
	
	@Embedded
	@ElementCollection
	@JoinTable(name="LikedByUsers",joinColumns = @JoinColumn (name = "user_id"))
	private Set<UserDto>likedByUsers =new HashSet<>();

	 private int likeCount;
	 
	
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


	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public LocalDateTime getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(LocalDateTime createdBy) {
		this.createdBy = createdBy;
	}


	public UserDto getUser() {
		return user;
	}


	public void setUser(UserDto user) {
		this.user = user;
	}


	public List<Comments> getComments() {
		return comments;
	}


	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}


	public Set<UserDto> getLikedByUsers() {
		return likedByUsers;
	}


	public void setLikedByUsers(Set<UserDto> likedByUsers) {
		this.likedByUsers = likedByUsers;
	}
	
	
}
