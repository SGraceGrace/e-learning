package com.project.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.elearning.exception.UserNotFoundException;
import com.project.elearning.model.User;
import com.project.elearning.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService service;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("all-users")
	public List<User> getAllUsers() throws UserNotFoundException {
			return service.getAllUsers();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("user")
	public User getUser(HttpServletRequest request) throws UserNotFoundException {
		String token = request.getHeader("Authorization").substring(7);
		return service.getUser(token);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PatchMapping("update")
	public String update(@RequestBody User u) throws UserNotFoundException {
		return service.updateUser(u);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("delete")
	public String delete(HttpServletRequest request) throws UserNotFoundException {
		String token = request.getHeader("Authorization").substring(7);
		return service.deleteUser(token);
	}
	
	@PutMapping("change-password")
	public String changePassword(@RequestParam String email, @RequestParam String newPassword) throws UserNotFoundException{
		return service.changePassword(email, newPassword);
	}
}
