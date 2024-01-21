package com.project.elearning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.elearning.model.Announcement;

public interface AnnouncementRepository extends MongoRepository<Announcement, String>{

}
