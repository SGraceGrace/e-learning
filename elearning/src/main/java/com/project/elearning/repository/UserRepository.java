package com.project.elearning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.elearning.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	User findByemail(String email);

	boolean existsByemail(String email);

	boolean existsByuserId(String userId);

	User findByuserId(String userId);

	void deleteByuserId(String userId);

	void deleteByemail(String email);

}
