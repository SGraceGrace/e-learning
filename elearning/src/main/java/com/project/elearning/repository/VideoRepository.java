package com.project.elearning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.elearning.model.Video;

public interface VideoRepository extends MongoRepository<Video, String>{

}
