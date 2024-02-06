package com.project.elearning.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.elearning.dto.LoginDTO;
import com.project.elearning.dto.ResponseDTO;
import com.project.elearning.dto.SignupDTO;
import com.project.elearning.exception.UserEmailAlreadyExistsException;
import com.project.elearning.exception.UserAlreadyExistsException;
import com.project.elearning.exception.UserIdAlreadyExistsException;
import com.project.elearning.exception.UserNotFoundException;
import com.project.elearning.jwtservice.JwtService;
import com.project.elearning.model.Role;
import com.project.elearning.model.User;
import com.project.elearning.repository.InstructorRepository;
import com.project.elearning.repository.UserRepository;
import com.project.elearning.service.AuthService;
import com.project.elearning.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository repository;

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	InstructorRepository repo;

	@Autowired
	UserService service;

	public ResponseDTO signup(SignupDTO signupDTO)
			throws UserIdAlreadyExistsException, UserEmailAlreadyExistsException, UserAlreadyExistsException {

		boolean check1 = service.existsByEmail(signupDTO.getEmail());
		boolean check2 = service.existsByUserId(signupDTO.getUserId());

		if (check1 && check2) {
			throw new UserAlreadyExistsException();
		} else if (check1) {
			throw new UserEmailAlreadyExistsException();
		} else if (check2) {
			throw new UserIdAlreadyExistsException();
		} else {
			User user;
			String token = "";

			if (signupDTO.getEmail().contains("admin")) {
				user = User.builder().userId(signupDTO.getUserId()).firstName("").lastName("").bio("").profession("").organisation("")
						.email(signupDTO.getEmail()).password(passwordEncoder.encode(signupDTO.getPassword()))
						.roles(Arrays.asList(Role.ADMIN)) .build();
			}else {
				user = User.builder().userId(signupDTO.getUserId()).firstName("").lastName("").bio("").profession("").organisation("")
						.email(signupDTO.getEmail()).password(passwordEncoder.encode(signupDTO.getPassword()))
						.roles(Arrays.asList(Role.USER)) .build();
			}
			repository.save(user);
			token = jwtService.generateToken(user);

			return ResponseDTO.builder().accessToken(token).build();

		}
	}

	public ResponseDTO login(LoginDTO loginDTO) throws UserNotFoundException {

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
			User user = repository.findByemail(loginDTO.getEmail());
			String token = jwtService.generateToken(user);
			return ResponseDTO.builder().accessToken(token).build();
		} catch (BadCredentialsException | UsernameNotFoundException e) {
			throw new UserNotFoundException();
		}
	}

}
