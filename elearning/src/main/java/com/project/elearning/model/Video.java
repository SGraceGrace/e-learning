package com.project.elearning.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "video")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Video {
	
	@Id
	private String _id;
	
	@Field("url")
	private String url;

}
