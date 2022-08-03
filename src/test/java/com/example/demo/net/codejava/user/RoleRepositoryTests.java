package com.example.demo.net.codejava.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	@Autowired private RoleRepository repo;
	
	@Test
	public void testCreateRoles() {
		Role associate = new Role("ROLE_ASSOCIATE");
		Role onboarding_reviewer = new Role("ROLE_ONBOARDING_REVIEWER");
		Role onboarding_manager = new Role("ROLE_ONBOARDING_MANAGER");
		List<Role> ls=new ArrayList<Role>();
		ls.add(associate);
		ls.add(onboarding_reviewer);
		ls.add(onboarding_manager);
		repo.saveAll(ls);
		
		long count = repo.count();
		assertEquals(3, count);
	}
}
