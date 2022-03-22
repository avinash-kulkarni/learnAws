package com.learn.frameworks.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document (collection = "users")
@Data
public class MyUser {

	private String username;
	private String password;
	private int enabled;
	private String role;
}
