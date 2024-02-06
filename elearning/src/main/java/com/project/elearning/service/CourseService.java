package com.project.elearning.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.elearning.dto.CourseDTO;
import com.project.elearning.exception.CourseAlreadyExistsException;
import com.project.elearning.exception.CourseNotFoundException;
import com.project.elearning.model.Course;
import com.project.elearning.model.Video;


public interface CourseService {

	boolean existsById(String courseUid);
	List<CourseDTO> getAllCourse() throws CourseNotFoundException;
	String updateCourse(Course c) throws CourseNotFoundException;
	List<CourseDTO> searchCourse(String search) throws CourseNotFoundException;
	String addReview(String courseUid, int rating, String token);
	Course getCourseById(String courseUid) throws CourseNotFoundException;
	String deleteCourse(String courseUid) throws CourseNotFoundException;
	String addCourse(Course c ,String token) throws CourseAlreadyExistsException;
	Video addVideo(MultipartFile video) throws IOException;
}
