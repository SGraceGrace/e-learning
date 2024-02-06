package com.project.elearning.model;


import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubModules {
	
	private String title;
	
	@DocumentReference
	private Video videoUrl;
	private int duration;
}
