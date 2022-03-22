package com.learn.frameworks.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document (collection = "Employee")
@Data
public class Employee {

	private String name;
	private String age;
	private List<String> devices;
	private List<Sofwares> softwares;
	
}
