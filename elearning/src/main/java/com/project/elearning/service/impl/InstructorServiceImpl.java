package com.project.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elearning.dao.DaoInterface;
import com.project.elearning.jwtservice.JwtService;
import com.project.elearning.model.Instructor;
import com.project.elearning.model.User;
import com.project.elearning.repository.InstructorRepository;
import com.project.elearning.repository.UserRepository;
import com.project.elearning.service.InstructorService;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	DaoInterface dao;
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	InstructorRepository repo;
	
	@Autowired
	JwtService jwtService;

	@Override
	public String addRatings(String id, int tutorialRatings) {
		dao.addTutorialRatings(id, tutorialRatings);
		return "Thank you for your ratings";
	}

	@Override
	public String addInstructor(String token, String name, String bio, int students) {
		String email = jwtService.extractUsername(token);
		User user = repository.findByemail(email);
		Instructor instructor = Instructor.builder().user(user).name(name).bio(bio).totalCourse(0).tutorialRatings(0).students(students).build();
		repo.save(instructor);
		dao.addInstructorRole(email);
		return "ADDED SUCCESSFULLY";
	}

}
