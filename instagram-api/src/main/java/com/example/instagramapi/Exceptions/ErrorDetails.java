package com.example.instagramapi.Exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {

	private String message;
	private String deatils;
	private LocalDateTime timestamp;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDeatils() {
		return deatils;
	}
	public void setDeatils(String deatils) {
		this.deatils = deatils;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
