package com.project.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
	private String courseUid;
	private String courseName;
	private String description;
	private double amount;
	private int reviews;
}
