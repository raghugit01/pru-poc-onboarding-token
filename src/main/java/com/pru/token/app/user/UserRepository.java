package com.pru.token.app.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmployeeId(String employeeId);
	
}
