package com.project.elearning.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "enrollment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

	@Id
	private String enrollmentId;
	
	@Field
	private String enrollmentUid;
	
	@DocumentReference
	@Field("student")
	private User student;
	
	@DocumentReference
	@Field("course")
	private Course course;
	
	@Field("enroll_date")
	private LocalDate enrollDate;
	
	@Field("module")
	private String module;
	
	@Field("status")
	private String status;
	
	@Field("completed_date")
	private String completedDate;
	
	@Field("score")
	private int score;
}
