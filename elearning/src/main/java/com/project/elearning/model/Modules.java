package com.project.elearning.model;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modules {
	
	private String title;
	private int duration;
	private String level;
	private ArrayList<SubModules> subModules;
	
}
