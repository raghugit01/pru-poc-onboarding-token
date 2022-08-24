package com.pru.token.app.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AssignRoleToUser {

	@Autowired
	private UserRepository repo;
	
	@Test 
	  public void testAssignRoleToUser() { 
		  Integer userId = 1; 
		  Integer roleId = 3;
		  Role role=new Role(roleId);
		  User user = repo.findById(userId).get(); 
		  user.setRole(role);
	  
		  User updatedUser = repo.save(user);
		  assertEquals(updatedUser.getRole(), role);
	  }
}
