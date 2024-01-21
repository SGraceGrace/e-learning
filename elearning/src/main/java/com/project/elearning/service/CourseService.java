package com.project.elearning.service;

import java.util.List;


import com.project.elearning.dto.CourseDTO;
import com.project.elearning.exception.CourseAlreadyExistsException;
import com.project.elearning.exception.CourseNotFoundException;
import com.project.elearning.model.Course;


public interface CourseService {

	String addCourse(Course c, String token) throws CourseAlreadyExistsException;
	boolean existsById(String courseUid);
	List<CourseDTO> getAllCourse() throws CourseNotFoundException;
	String updateCourse(Course c) throws CourseNotFoundException;
	List<CourseDTO> searchCourse(String search) throws CourseNotFoundException;
	String addReview(String courseUid, int rating, String token);
	Course getCourseById(String courseUid) throws CourseNotFoundException;
	String deleteCourse(String courseUid) throws CourseNotFoundException;
}
