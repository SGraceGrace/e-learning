package com.project.elearning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.elearning.model.Instructor;
import com.project.elearning.model.User;

public interface InstructorRepository extends MongoRepository<Instructor, String>{

	Instructor findByuser(User user);

}
