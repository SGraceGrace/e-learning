package com.project.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elearning.dao.DaoInterface;
import com.project.elearning.service.InstructorService;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	DaoInterface dao;

	@Override
	public String addRatings(String id, int tutorialRatings) {
		dao.addTutorialRatings(id, tutorialRatings);
		return "Thank you for your ratings";
	}

}
