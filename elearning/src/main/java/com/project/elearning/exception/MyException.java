package com.project.elearning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyException {

	@ExceptionHandler(value = UserIdAlreadyExistsException.class)
	public ResponseEntity<Object> userIdExistsException(UserIdAlreadyExistsException userExists) {
		return new ResponseEntity<Object>("USER ID ALREADY EXISTS", HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = UserEmailAlreadyExistsException.class)
	public ResponseEntity<Object> userEmailExistsException(UserEmailAlreadyExistsException userExists) {
		return new ResponseEntity<Object>("USER EMAIL ALREADY EXISTS", HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<Object> userExistsException(UserAlreadyExistsException userExists){
		return new ResponseEntity<Object>("USER ALREADY EXISTS", HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException userNotFound){
		return new ResponseEntity<Object>("USER NOT FOUND", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CourseAlreadyExistsException.class)
	public ResponseEntity<Object> courseAlreadyExistsException(CourseAlreadyExistsException courseExists){
		return new ResponseEntity<Object>("COURSE ALREADY EXISTS", HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = CourseNotFoundException.class)
	public ResponseEntity<Object> courseNotFoundException(CourseNotFoundException CourseNotFound){
		return new ResponseEntity<Object>("COURSE NOT FOUND", HttpStatus.NOT_FOUND);
	}
}
