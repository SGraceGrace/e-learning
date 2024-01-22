package com.project.elearning.model;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

	@Id
	private String id;
	
	@Field("course_uid")
	private String courseUid;
	
	@Field("course_name")
	private String courseName;
	
	@Field("description")
	private String description;
	
	@Field("modules")
	private ArrayList<Modules> modules;
	
	@Field("start_date")
	private String startDate;
	
	@Field("end_date")
	private String endDate;
	
	@Field("amount")
	private double amount;
	
	@Field("review")
	private ArrayList<Review> review;
	
	@Field("published_on")
	private LocalDate published;
	
	@Field("total_enrollments")
	private int totalEnrollments;
	
	@DocumentReference
	@Field("instructor")
	private Instructor instructor;
	
	@Field("assignment")
	private Assignment assingment;
	
}
