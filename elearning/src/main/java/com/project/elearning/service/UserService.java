package com.project.elearning.service;

import java.util.List;

import com.project.elearning.exception.UserNotFoundException;
import com.project.elearning.model.User;

public interface UserService {

	List<User> getAllUsers() throws UserNotFoundException;

	boolean existsByEmail(String email);

	boolean existsByUserId(String userId);

	User getUser(String userId) throws UserNotFoundException;

	String updateUser(User u) throws UserNotFoundException;

	String deleteUser(String token) throws UserNotFoundException;

	String changePassword(String email, String newPassword) throws UserNotFoundException;

}
