package com.project.elearning.service;

import com.project.elearning.dto.LoginDTO;
import com.project.elearning.dto.ResponseDTO;
import com.project.elearning.dto.SignupDTO;
import com.project.elearning.exception.UserEmailAlreadyExistsException;
import com.project.elearning.exception.UserAlreadyExistsException;
import com.project.elearning.exception.UserIdAlreadyExistsException;
import com.project.elearning.exception.UserNotFoundException;

public interface AuthService {
	ResponseDTO signup(SignupDTO signupDTO) throws UserIdAlreadyExistsException, UserEmailAlreadyExistsException, UserAlreadyExistsException;
	ResponseDTO login(LoginDTO loginDTO) throws UserNotFoundException;

}
