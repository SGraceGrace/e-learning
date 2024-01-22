package com.project.elearning.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elearning.dao.DaoInterface;
import com.project.elearning.dto.EnrollmentsDTO;
import com.project.elearning.exception.CourseNotFoundException;
import com.project.elearning.jwtservice.JwtService;
import com.project.elearning.model.Course;
import com.project.elearning.model.Enrollment;
import com.project.elearning.model.User;
import com.project.elearning.repository.CourseRepository;
import com.project.elearning.repository.EnrollmentRepository;
import com.project.elearning.repository.UserRepository;
import com.project.elearning.service.EnrollmentService;

@Service
public class EnrollmentSeviceImpl implements EnrollmentService {

	@Autowired
	EnrollmentRepository repository;

	@Autowired
	UserRepository repo;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	DaoInterface dao;

	@Autowired
	JwtService jwtService;

	@Override
	public String add(Enrollment e, String courseUid, String token) throws CourseNotFoundException {
		String email = jwtService.extractUsername(token);

		User user = repo.findByemail(email);
		e.setStudent(user);

		Course course = courseRepository.findBycourseUid(courseUid);

		if (course != null) {
			e.setCourse(course);

			e.setEnrollDate(LocalDate.now());
			e.setStarted(false);
			e.setModule("Not Started");
			e.setStatus("Not Started");
			e.setCompletedDate("Not Started");
			e.setScore(0);

			repository.save(e);

			dao.increaseEnrollment(courseUid);

			return "ENROLLED SUCCESSFULLY";
		} else {
			throw new CourseNotFoundException();
		}
	}

	@Override
	public List<Enrollment> getAllEnrollments(String token) {
		String email = jwtService.extractUsername(token);
		return dao.findAllEnrollments(email);
	}

	@Override
	public String deleteEnrollment(String enrollmentUId, String courseid) {
		repository.deleteByenrollmentUid(enrollmentUId);
		dao.decreaseEnroll(courseid);
		return "DELETED SUCCESSFULLY";
	}

	@Override
	public List<EnrollmentsDTO> getEnrollments(String id) {
		return dao.getEnrollments(id);
	}

}
