package com.project.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.elearning.dto.CourseDTO;
import com.project.elearning.exception.CourseAlreadyExistsException;
import com.project.elearning.exception.CourseNotFoundException;
import com.project.elearning.model.Course;
import com.project.elearning.service.CourseService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("course")
public class CourseController {

	@Autowired
	CourseService service;

	@PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
	@PostMapping("add-course")
	public String addCourse(@RequestBody Course c, HttpServletRequest request) throws CourseAlreadyExistsException {
		String token = request.getHeader("Authorization").substring(7);
		return service.addCourse(c, token);
	}

	@GetMapping("all-course")
	public List<CourseDTO> getAllCourse() throws CourseNotFoundException {
		return service.getAllCourse();
	}

	@GetMapping("id/{courseUid}")
	public Course getCourseById(@PathVariable String courseUid) throws CourseNotFoundException {
		return service.getCourseById(courseUid);
	}

	@GetMapping("search-course")
	public List<CourseDTO> getCourse(@RequestParam String search) throws CourseNotFoundException {
		return service.searchCourse(search);
	}
	
	@PutMapping("review/{courseUid}")
	public String addReview(@PathVariable String courseUid, @RequestParam int rating, HttpServletRequest request) {
		String token = request.getHeader("Authorization").substring(7);
		return service.addReview(courseUid, rating, token);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
	@PutMapping("update-course")
	public String updateCourse(@RequestBody Course c) throws CourseNotFoundException {
		return service.updateCourse(c);
	}

	@DeleteMapping("delete/{courseUid}")
	public String deleteCourse(@PathVariable String courseUid) throws CourseNotFoundException{
		return service.deleteCourse(courseUid);
	}
}
