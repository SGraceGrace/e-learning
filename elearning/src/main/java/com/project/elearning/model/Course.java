package com.project.elearning.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

	@Id
	private String id;
	
	@Field("course_uid")
	private String courseUid;
	
	@Field("course_name")
	private String courseName;
	
	@Field("brief-description")
	private String briefDescription;
	
	@Field("description")
	private String description;
	
	@Field("modules")
	private List<Modules> modules;
	
	@Field("amount")
	private double amount;
	
	@Field("review")
	private List<Review> review;
	
	@Field("published_on")
	private LocalDate published;
	
	@Field("total_enrollments")
	private int totalEnrollments;
	
	@Field("max-enrollment")
	private int maxEnrollments;
	
	@DocumentReference
	@Field("instructor")
	private Instructor instructor;
	
	@Field("assignment")
	private Assignment assingment;
	
}
