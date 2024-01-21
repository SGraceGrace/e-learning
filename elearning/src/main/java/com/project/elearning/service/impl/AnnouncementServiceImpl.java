package com.project.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elearning.jwtservice.JwtService;
import com.project.elearning.model.Announcement;
import com.project.elearning.repository.AnnouncementRepository;
import com.project.elearning.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AnnouncementRepository repository;

	@Override
	public String addAnnouncement(String token, Announcement announcement) {
		String username = jwtService.extractUsername(token);
        announcement.setUsername(username);
        repository.save(announcement);
		return "ANNOUNCEMENT ADDED";
	}

}
