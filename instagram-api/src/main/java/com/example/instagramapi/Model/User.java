package com.example.instagramapi.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.example.instagramapi.DTO.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_users")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String email;
	private String mobile;
	private String username;
	private String bio;
	private String gender;
	private String image;
	private String password;
    private boolean isActive = true;

	@Embedded
	@ElementCollection
	private Set<UserDto> follower = new HashSet<UserDto>();
	
	@Embedded
	@ElementCollection
	private Set<UserDto> following = new HashSet<UserDto>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Story> stories = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Post> savePost = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Story> saveStory = new ArrayList<>();
	
	 @JsonIgnore
	@ElementCollection
	 @Column(name = "blocked_user_ids")
	  private List<Integer> blockedUserIds = new ArrayList<>();
	
	
	public List<Story> getSaveStory() {
		return saveStory;
	}
	public void setSaveStory(List<Story> saveStory) {
		this.saveStory = saveStory;
	}
	
	
	
	public List<Integer> getBlockedUserIds() {
		return blockedUserIds;
	}
	public void setBlockedUserIds(List<Integer> blockedUserIds) {
		this.blockedUserIds = blockedUserIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<UserDto> getFollower() {
		return follower;
	}
	public void setFollower(Set<UserDto> follower) {
		this.follower = follower;
	}
	public Set<UserDto> getFollowing() {
		return following;
	}
	public void setFollowing(Set<UserDto> following) {
		this.following = following;
	}
	public List<Story> getStories() {
		return stories;
	}
	public void setStories(List<Story> stories) {
		this.stories = stories;
	}
	public List<Post> getSavePost() {
		return savePost;
	}
	public void setSavePost(List<Post> savePost) {
		this.savePost = savePost;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
