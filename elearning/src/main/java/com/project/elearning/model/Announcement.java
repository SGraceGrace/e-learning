package com.project.elearning.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "announcement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
	
	@Id
	private String id;

	@Field("username")
	private String username;
	
	@Field("announcement")
	private String announcement;
}
