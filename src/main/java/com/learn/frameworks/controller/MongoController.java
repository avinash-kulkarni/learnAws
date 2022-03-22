package com.learn.frameworks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.frameworks.model.Employee;
import com.learn.frameworks.service.MongoService;

@RestController
public class MongoController {

	@Autowired
	MongoService mongoService;
	

	@PostMapping(value = "/insert")
	public void insertEmployee(@RequestBody Employee employee)  {
		mongoService.insertEmployee(employee);
	}
	
	@GetMapping(value = "/findAll/{name}")
	public List<Employee> getAllFileds(@PathVariable String name) {
		return mongoService.getAllNames(name);
	}
	
}
