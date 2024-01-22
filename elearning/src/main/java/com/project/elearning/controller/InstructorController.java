package com.project.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.elearning.service.InstructorService;

@RestController
@RequestMapping("instructor")
public class InstructorController {
	
	@Autowired
	InstructorService service;

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	@PostMapping("add-ratings/{id}")
	public String addRatings(@PathVariable String id,  @RequestParam int tutorialRatings) {
		return service.addRatings(id, tutorialRatings);
		
	}
}
