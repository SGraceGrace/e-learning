package com.project.elearning.service;

public interface InstructorService {

	String addRatings(String id, int tutorialRatings);

	String addInstructor(String token, String name, String bio, int students);

}
