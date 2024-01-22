package com.project.elearning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.project.elearning.dao.DaoInterface;
import com.project.elearning.dto.EnrollmentsDTO;
import com.project.elearning.model.Course;
import com.project.elearning.model.Enrollment;
import com.project.elearning.model.Instructor;
import com.project.elearning.model.Review;
import com.project.elearning.model.User;
import com.project.elearning.repository.CourseRepository;
import com.project.elearning.repository.UserRepository;

@Repository
public class DaoImpl implements DaoInterface {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	UserRepository repository;

	@Autowired
	CourseRepository repo;

	public void changePassword(String email, String newPassword) {

		Criteria criteria = Criteria.where("email").is(email);

		Query query = new Query(criteria);

		Update update = new Update().set("password", newPassword);

		mongoTemplate.updateFirst(query, update, User.class);

	}

	@Override
	public List<Course> searchCourse(String search) {

		Criteria criteria = Criteria.where("course_name").regex(search, "i");

		Query query = new Query(criteria);

		return mongoTemplate.find(query, Course.class);
	}

	@Override
	public void addReview(String courseUid, Review review) {

		Criteria criteria = Criteria.where("course_uid").is(courseUid);

		Query query = new Query(criteria);

		Update update = new Update().push("review", review);

		mongoTemplate.updateFirst(query, update, Course.class);

	}

	@Override
	public List<Enrollment> findAllEnrollments(String email) {

		User user = repository.findByemail(email);
		Criteria criteria = Criteria.where("student").is(user);

		Query query = new Query(criteria);

		return mongoTemplate.find(query, Enrollment.class);
	}

	@Override
	public void increaseEnrollment(String courseUid) {

		Criteria criteria = Criteria.where("course_uid").is(courseUid);

		Query query = new Query(criteria);

		Update update = new Update().inc("totalEnrollments", 1);

		mongoTemplate.updateMulti(query, update, Course.class);
		
		Course course = repo.findBycourseUid(courseUid);
		
		Criteria criteria2 = Criteria.where("id").is(course.getInstructor().getId());
		
		Query query2 = new Query(criteria2);
		
		Update update2 = new Update().inc("total_students", 1);
		
		mongoTemplate.updateMulti(query2, update2, Instructor.class);
	}

	@Override
	public void decreaseEnroll(String courseid) {
		Criteria criteria = Criteria.where("course_uid").is(courseid);

		Query query = new Query(criteria);

		Update update = new Update().inc("totalEnrollments", -1);

		mongoTemplate.updateMulti(query, update, Course.class);
	}

	@Override
	public List<EnrollmentsDTO> getEnrollments(String id) {

		Course course = repo.findBycourseUid(id);
		Criteria criteria = Criteria.where("course").is(course);

		Query query = new Query(criteria);

		List<Enrollment> list = new ArrayList<Enrollment>();

		list = mongoTemplate.find(query, Enrollment.class);

		List<EnrollmentsDTO> enroll = new ArrayList<EnrollmentsDTO>();
		
		for (Enrollment enrollment : list) {
			EnrollmentsDTO enrollmentsDTO = EnrollmentsDTO.builder().username(enrollment.getStudent().getUserId())
					.enrollDate(enrollment.getEnrollDate()).status(enrollment.getStatus()).build();
			
			enroll.add(enrollmentsDTO);
		}

		return enroll;
	}

	@Override
	public void addTutorialRatings(String id, int tutorialRatings) {
		
		Course course = repo.findBycourseUid(id);

		Instructor instructor = course.getInstructor();
		
		Criteria criteria = Criteria.where("instructor").is(instructor);
		
		Query query = new Query(criteria);
		
		Update upadte = new Update().inc("tutorial_ratings", tutorialRatings);
		
		mongoTemplate.updateFirst(query, upadte, Instructor.class);
		
		
	}

	@Override
	public void increaseCourse(String id) {
		
		Criteria criteria = Criteria.where("id").is(id);
		
		Query query = new Query(criteria);
		
		Update update = new Update().inc("total-courses", 1);
		
		mongoTemplate.updateFirst(query, update, Instructor.class);
	}
}
