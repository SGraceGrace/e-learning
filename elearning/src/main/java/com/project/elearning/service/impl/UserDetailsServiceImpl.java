package com.project.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.project.elearning.model.User;
import com.project.elearning.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		User user = userRepository.findByemail(email);
		
		if (user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("USER NOT EXSISTS");
		}
		
		
	}

}
