package com.example.demo.net.codejava.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.pru.token.app.user.Role;
import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AssignRoleToUser {

	@Autowired
	private UserRepository repo;
	
	@Test 
	  public void testAssignRoleToUser() { 
		  Integer userId = 4; 
		  Integer roleId = 1;
		  Role role=new Role(roleId);
		  User user = repo.findById(userId).get(); 
		  user.setRole(role);
	  
		  User updatedUser = repo.save(user);
		  assertEquals(updatedUser.getRole(), role);
	  }
}
