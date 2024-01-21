package com.project.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.elearning.model.Announcement;
import com.project.elearning.service.AnnouncementService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AnnouncementController {
	
	@Autowired
	AnnouncementService service;

	@PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
	@PostMapping("add-announcement")
	public String addAnnouncement(HttpServletRequest request, @RequestBody Announcement announcement) {
		String token = request.getHeader("Authorization").substring(7);
		return service.addAnnouncement(token, announcement);
	}
}
