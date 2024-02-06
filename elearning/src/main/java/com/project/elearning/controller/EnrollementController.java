package com.project.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.elearning.dao.DaoInterface;
import com.project.elearning.dto.EnrollmentsDTO;
import com.project.elearning.exception.CourseNotFoundException;
import com.project.elearning.exception.EnrollmentIsFullException;
import com.project.elearning.model.Enrollment;
import com.project.elearning.service.EnrollmentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("enrollment")
public class EnrollementController {

	@Autowired
	EnrollmentService service;

	@Autowired
	DaoInterface dao;

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@PostMapping("add-enrollment/{id}")
	public String saveEnrollment(@RequestBody Enrollment enroll, @PathVariable String id, HttpServletRequest request)
			throws CourseNotFoundException, EnrollmentIsFullException {
		String token = request.getHeader("Authorization").substring(7);
		return service.add(enroll, id, token);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@GetMapping("all-enrolls")
	public List<Enrollment> getMyEnrollment(HttpServletRequest request) {
		String token = request.getHeader("Authorization").substring(7);
		return service.getAllEnrollments(token);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@DeleteMapping("delete/{enrollid}/{courseid}")
	public String deleteEnrollment(@PathVariable String enrollid, @PathVariable String courseid) {
		return service.deleteEnrollment(enrollid, courseid);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
	@GetMapping("getallenroll/{id}")
	public List<EnrollmentsDTO> getCourseEnrollment(@PathVariable String id){
		return service.getEnrollments(id);
	}
}
