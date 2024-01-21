package com.project.elearning.dao;

import java.util.List;

import com.project.elearning.dto.EnrollmentsDTO;
import com.project.elearning.model.Course;
import com.project.elearning.model.Enrollment;
import com.project.elearning.model.Review;

public interface DaoInterface {
	
	void changePassword(String email, String newPassword);

	List<Course> searchCourse(String search);

	void addReview(String courseUid, Review review);

	List<Enrollment> findAllEnrollments(String email);

	void increaseEnrollment(String courseUid);

	void decreaseEnroll(String courseid);

	List<EnrollmentsDTO> getEnrollments(String id);

}
