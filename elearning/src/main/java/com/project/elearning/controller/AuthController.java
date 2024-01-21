package com.project.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.elearning.dto.LoginDTO;
import com.project.elearning.dto.ResponseDTO;
import com.project.elearning.dto.SignupDTO;
import com.project.elearning.exception.UserEmailAlreadyExistsException;
import com.project.elearning.exception.UserAlreadyExistsException;
import com.project.elearning.exception.UserIdAlreadyExistsException;
import com.project.elearning.exception.UserNotFoundException;
import com.project.elearning.jwtservice.JwtService;
import com.project.elearning.service.AuthService;

@RestController
public class AuthController {

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AuthService service;

	
	@PostMapping("/signup")
	public ResponseDTO signup(@RequestBody SignupDTO signupDTO) throws UserIdAlreadyExistsException,UserEmailAlreadyExistsException, UserAlreadyExistsException {
		return service.signup(signupDTO);	
	}
	
	@PostMapping("/login")
	public ResponseDTO login(@RequestBody LoginDTO loginDTO) throws UserNotFoundException {
		return service.login(loginDTO);
	}

}
