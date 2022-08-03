package com.example.demo.net.codejava.user;

import static org.assertj.core.api.Assertions.assertThat;

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
		  Integer userId = 11; 
		  Integer roleId = 13;
		  User user = repo.findById(userId).get(); 
		  user.addRole(new Role(roleId));
	  
		  User updatedUser = repo.save(user);
		  int size = updatedUser.getRoles().size();
		  assertThat(updatedUser.getRoles()).hasSize(size);	  
	  }
}
