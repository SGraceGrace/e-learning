package com.project.elearning.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "instructor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instructor{

	@Id
	private String id;
	
	@DocumentReference
	@Field("user")
	private User user;
	
	@Field("name")
	private String name;
	
	@Field("description")
	private String description;
	
	@Field("tutorial_ratings")
	private double tutorialRatings;
		
	@Field("total-courses")
	private int totalCourse;
	
	@Field("total_students")
	private int students;
}
