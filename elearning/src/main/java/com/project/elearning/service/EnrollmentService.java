package com.project.elearning.service;

import java.util.List;

import com.project.elearning.dto.EnrollmentsDTO;
import com.project.elearning.exception.CourseNotFoundException;
import com.project.elearning.model.Enrollment;

public interface EnrollmentService {

	String add(Enrollment e, String courseUid, String token) throws CourseNotFoundException;
	List<Enrollment> getAllEnrollments(String token);
	String deleteEnrollment(String enrollmentUId, String courseid);
	List<EnrollmentsDTO> getEnrollments(String id);
}
