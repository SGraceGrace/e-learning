package com.project.elearning.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elearning.dao.DaoInterface;
import com.project.elearning.dto.CourseDTO;
import com.project.elearning.exception.CourseAlreadyExistsException;
import com.project.elearning.exception.CourseNotFoundException;
import com.project.elearning.jwtservice.JwtService;
import com.project.elearning.model.Course;
import com.project.elearning.model.Review;
import com.project.elearning.model.User;
import com.project.elearning.repository.CourseRepository;
import com.project.elearning.repository.UserRepository;
import com.project.elearning.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository repository;

	@Autowired
	UserRepository repo;

	@Autowired
	DaoInterface dao;

	@Autowired
	JwtService jwtService;

	@Override
	public String addCourse(Course c, String token) throws CourseAlreadyExistsException {

		String email = jwtService.extractUsername(token);
		User user = repo.findByemail(email);

		if (existsById(c.getCourseUid())) {
			throw new CourseAlreadyExistsException();
		} else {
			c.setPublished(LocalDate.now());
			c.setInstructor(user);
			repository.save(c);
			return "COURSE ADDED SUCCESSFULLY";
		}
	}

	@Override
	public List<CourseDTO> getAllCourse() throws CourseNotFoundException {
		List<Course> list = repository.findAll();
		List<CourseDTO> courses = new ArrayList<>();

		if (list != null) {

			for (Course course : list) {
				CourseDTO courseDTO = CourseDTO.builder().courseUid(course.getCourseUid())
						.courseName(course.getCourseName()).description(course.getDescription())
						.amount(course.getAmount()).reviews(4).build();
				courses.add(courseDTO);
			}

			return courses;
		} else {
			throw new CourseNotFoundException();
		}
	}

	@Override
	public List<CourseDTO> searchCourse(String search) throws CourseNotFoundException {
		List<Course> list = dao.searchCourse(search);
		List<CourseDTO> courses = new ArrayList<>();

		if (list != null) {

			for (Course course : list) {
				CourseDTO courseDTO = CourseDTO.builder().courseUid(course.getCourseUid())
						.courseName(course.getCourseName()).description(course.getDescription())
						.amount(course.getAmount()).reviews(5).build();
				courses.add(courseDTO);
			}

			return courses;
		} else {
			throw new CourseNotFoundException();
		}
	}

	@Override
	public boolean existsById(String courseUid) {
		return repository.existsBycourseUid(courseUid);
	}

	@Override
	public String updateCourse(Course c) throws CourseNotFoundException {
		if (existsById(c.getCourseUid())) {
			repository.save(c);
			return "UPDATED SUCCESSFULLY";
		} else {
			throw new CourseNotFoundException();
		}
	}

	@Override
	public String addReview(String courseUid, int rating, String token) {
		String email = jwtService.extractUsername(token);
		String name = email.split("@")[0];
		Review review = Review.builder().rating(rating).name(name).build();
		dao.addReview(courseUid, review);
		return "Thank You for your Ratings";
	}

	@Override
	public Course getCourseById(String courseUid) throws CourseNotFoundException {
		Course course = repository.findBycourseUid(courseUid);
		if (course != null) {
			return course;
		} else {
			throw new CourseNotFoundException();
		}
	}

	@Override
	public String deleteCourse(String courseUid) throws CourseNotFoundException{
		if (existsById(courseUid)) {
		repository.deleteBycourseUid(courseUid);
		return "DELETED SUCCESSFULLY";
		}else {
			throw new CourseNotFoundException();
		}
	}

}
