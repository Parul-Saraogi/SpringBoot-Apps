package com.example.instagramapi.DTO;

import java.util.Objects;

import jakarta.persistence.Embeddable;

public class UserDto {

	private Integer id;
	private String username;
	private String email;
	private String name;
	private String userImage;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        UserDto userDto = (UserDto) o;
	        return Objects.equals(id, userDto.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }
	
//	    
//	    ==================//Why Override equals and hashCode?===========
//	    When working with collections in Java, such as HashSet, HashMap, and other hash-based collections, these methods play a vital role in ensuring the correct behavior of the collections.
//
//	    equals Method
//	    Purpose: The equals method checks if two objects are considered "equal."
//	    Usage in Collections: When you use methods like contains, remove, or other operations that involve checking for the presence of an object in a collection, the equals method is used to determine if two objects are the same.
//	    hashCode Method
//	    Purpose: The hashCode method returns an integer representation of the object. This hash code is used in hashing-based collections.
//	    Usage in Collections: Hash-based collections like HashSet and HashMap use the hash code to quickly locate a bucket where the object might be stored. When you add an object to a HashSet, the collection uses the hash code to determine the bucket location, and then it uses equals to check if the object already exists in that bucket.
//	    Importance in Your Case
//	    In your scenario, you needed to remove a UserDto object from a Set. The Set relies on the equals and hashCode methods to identify the object to be removed.
//
//	    equals Implementation:
//
//	    Purpose: Ensure that two UserDto objects are considered equal if their id fields are the same.
//	    Effect: This allows the Set to find and compare UserDto objects based on their IDs, ensuring correct identification of the object to remove.
//	    hashCode Implementation:
//
//	    Purpose: Ensure that the hash code is consistent with equals. If two objects are equal, they must have the same hash code.
//	    Effect: This allows the Set to correctly locate the bucket for the UserDto object and perform operations like remove efficiently.
//	    Code Explanation

	
}
