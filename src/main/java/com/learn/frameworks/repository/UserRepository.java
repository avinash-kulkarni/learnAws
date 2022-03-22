package com.learn.frameworks.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learn.frameworks.model.MyUser;

public interface UserRepository extends MongoRepository<MyUser, String> {
	
	public List<MyUser> findByUsername(String username);

}
