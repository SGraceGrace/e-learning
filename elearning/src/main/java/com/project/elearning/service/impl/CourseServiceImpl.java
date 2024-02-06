package com.project.elearning.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.elearning.dao.DaoInterface;
import com.project.elearning.dto.CourseDTO;
import com.project.elearning.exception.CourseAlreadyExistsException;
import com.project.elearning.exception.CourseNotFoundException;
import com.project.elearning.jwtservice.JwtService;
import com.project.elearning.model.Course;
import com.project.elearning.model.Instructor;
import com.project.elearning.model.Review;
import com.project.elearning.model.User;
import com.project.elearning.model.Video;
import com.project.elearning.repository.CourseRepository;
import com.project.elearning.repository.InstructorRepository;
import com.project.elearning.repository.UserRepository;
import com.project.elearning.repository.VideoRepository;
import com.project.elearning.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository repository;
	
	@Autowired
	InstructorRepository repository2;
	
	@Autowired
	VideoRepository repository3;

	@Autowired
	UserRepository repo;

	@Autowired
	DaoInterface dao;

	@Autowired
	JwtService jwtService;
	
	@Value("${upload.folder}")
	String upload;

	@Override
	public String addCourse(Course c, String token) throws CourseAlreadyExistsException {

		String email = jwtService.extractUsername(token);
		User user = repo.findByemail(email);
		Instructor instructor = repository2.findByuser(user);

		if (existsById(c.getCourseUid())) {
			throw new CourseAlreadyExistsException();
		} else {
			c.setReview(new ArrayList<>());
			c.setPublished(LocalDate.now());
			c.setTotalEnrollments(0);
			c.setInstructor(instructor);
			c.setMaxEnrollments(100);
			repository.save(c);
			dao.increaseCourse(instructor.getId());
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
						.briefDescription(course.getBriefDescription())
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
						.briefDescription(course.getBriefDescription())
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

	public Video addVideo(MultipartFile video) throws IOException {
		String s = video.getOriginalFilename();	
		String fileName = upload + File.separator + s;
		Files.copy(video.getInputStream(), Paths.get(upload + File.separator + s));
		
		Video video2 = Video.builder().url(fileName).build();
		Video savedVideo =  repository3.save(video2);
		System.out.println(savedVideo.get_id());
		return savedVideo;
	}

}
