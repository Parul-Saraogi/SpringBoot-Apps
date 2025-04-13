package com.example.instagramapi.Exceptions;



import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails>  UserException (UserException ex ,WebRequest web){
		
		ErrorDetails errror = new ErrorDetails();
		errror.setMessage(ex.getMessage());
		errror.setDeatils(web.getDescription(false));
		errror.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(errror,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorDetails>  PostException (PostException ex ,WebRequest web){
		
		ErrorDetails errror = new ErrorDetails();
		errror.setMessage(ex.getMessage());
		errror.setDeatils(web.getDescription(false));
		errror.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ErrorDetails>(errror,HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(CommentException.class)
	public ResponseEntity<ErrorDetails>  CommentException (CommentException ex ,WebRequest web){
		
		ErrorDetails errror = new ErrorDetails();
		errror.setMessage(ex.getMessage());
		errror.setDeatils(web.getDescription(false));
		errror.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ErrorDetails>(errror,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StoryException.class)
	public ResponseEntity<ErrorDetails>  StoryException (StoryException ex ,WebRequest web){
		
		ErrorDetails errror = new ErrorDetails();
		errror.setMessage(ex.getMessage());
		errror.setDeatils(web.getDescription(false));
		errror.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ErrorDetails>(errror,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	//Used when Validation failed 
	//maximum 2 charcter but failed
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails>  MethodArgumentNotValidException (MethodArgumentNotValidException ex ,WebRequest web){
		
		ErrorDetails errror = new ErrorDetails();
		errror.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
		errror.setDeatils("Validation Error");
		errror.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(errror,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails>  otherExceptionException (Exception ex ,WebRequest web){
		
		ErrorDetails errror = new ErrorDetails();
		errror.setMessage(ex.getMessage());
		errror.setDeatils(web.getDescription(false));
		errror.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(errror,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
	
}
