package com.learn.frameworks.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.learn.frameworks.model.Employee;

public interface MongoDBRepository extends MongoRepository<Employee, String> {
	
	@Query("{'name':?0}")
	public List<Employee> findByName(String name);

}
