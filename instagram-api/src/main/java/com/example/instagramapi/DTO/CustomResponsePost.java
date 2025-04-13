package com.example.instagramapi.DTO;

import com.example.instagramapi.Model.Post;

public class CustomResponsePost {
	private Post post;
    private String message;

    public CustomResponsePost() {
		// TODO Auto-generated constructor stub
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	// Constructors
    public CustomResponsePost(Post post, String message) {
        this.post = post;
        this.message = message;
    }
}
