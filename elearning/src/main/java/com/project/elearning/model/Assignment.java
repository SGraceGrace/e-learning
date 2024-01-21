package com.project.elearning.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {

	private String title;
	private ArrayList<Questions> questions;
	private int duration;
}
