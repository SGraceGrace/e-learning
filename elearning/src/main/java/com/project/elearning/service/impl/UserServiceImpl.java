package com.project.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.elearning.dao.DaoInterface;
import com.project.elearning.exception.UserNotFoundException;
import com.project.elearning.jwtservice.JwtService;
import com.project.elearning.model.User;
import com.project.elearning.repository.UserRepository;
import com.project.elearning.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	DaoInterface dao;
	
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUsers() throws UserNotFoundException {
		List<User> users = repository.findAll();
		if (users != null) {
			return users;
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public boolean existsByEmail(String email) {
		return repository.existsByemail(email);
	}

	@Override
	public boolean existsByUserId(String userId) {
		return repository.existsByuserId(userId);
	}

	@Override
	public User getUser(String userId) throws UserNotFoundException{
		User user = repository.findByuserId(userId);
		if(user!=null) {
			return user;
		}else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public String updateUser(User u) throws UserNotFoundException{
		if (existsByEmail(u.getEmail())) {
			repository.save(u);
			return "UPDATED SUCCESSFULLY";
		}else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public String deleteUser(String token) throws UserNotFoundException{
		String email = jwtService.extractUsername(token);
		if (existsByEmail(email)) {
			repository.deleteByemail(email);
			return "DELETED SUCCESSFULLY";
		}else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public String changePassword(String email, String newPassword) throws UserNotFoundException{
		newPassword = passwordEncoder.encode(newPassword);
		if (existsByEmail(email)) {
			dao.changePassword(email, newPassword);
			return "PASSWORD CHANGED SUCCESSFULLY";
		}else {
			throw new UserNotFoundException();
		}
	}
}
