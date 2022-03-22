package com.learn.frameworks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.frameworks.model.Employee;
import com.learn.frameworks.repository.MongoDBRepository;

@Service
public class MongoService {

	@Autowired
	MongoDBRepository mongoDBRepository;

	public List<Employee> getAllNames(String name) {
		return mongoDBRepository.findByName(name);
	}

	public void insertEmployee(Employee employee) {
		mongoDBRepository.insert(employee);
	}

}
