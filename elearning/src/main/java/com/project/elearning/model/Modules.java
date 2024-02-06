package com.project.elearning.model;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Modules {
	
	private String title;
	private int duration;
	private String level;
	private ArrayList<SubModules> subModules;
	
}
