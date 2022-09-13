package com.pru.token.app.user.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pru.token.app.user.Manager;
import com.pru.token.app.user.ManagerRepository;
import com.pru.token.app.user.Reviewer;
import com.pru.token.app.user.ReviewerRepository;
import com.pru.token.app.user.Role;
import com.pru.token.app.user.RoleRepository;
import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserApiTest {
	
	@InjectMocks
	private UserApi api;
	
	@Mock 
	private UserService service;
	
	@Mock
	private RoleRepository rolerepo;
	
	@Mock
	private ManagerRepository manrepo;
	
	@Mock
	private ReviewerRepository reviwrepo;
	
	@Mock
	private UserRepository usrrepo;
	
	@Mock
	private PasswordEncoder encoder;

	@Test
	void testCreateUser() {
		User user=new User("1", "w", "e", "we", "lp", null, null, null);
		when(service.save(user)).thenReturn(user);
		ResponseEntity<UserDTO> rsp = api.createUser(user);
		System.out.println("rs "+rsp.getBody());
		assertNotNull(rsp);		
		assertEquals("w", rsp.getBody().getEmail());
	}

	@Test
	void testAddUser() {
		Role role=new Role("12", "qa");
		Manager manager=new Manager("s", "wwd");
		Reviewer reviewer=new Reviewer("rn", "rv");
		RequestUser requestUser = new RequestUser("1", "w", "e", "we", "lp", "un", "pw", "12");
		Optional<Role> rol=Optional.of(role);
		when(rolerepo.findById(requestUser.getRoleId())).thenReturn(rol);
		when(manrepo.findByEmpId(requestUser.getManagerEmpId())).thenReturn(manager);
		when(reviwrepo.findByEmpId(requestUser.getReviewerEmpId())).thenReturn(reviewer);
		ResponseEntity<User> rspu = api.addUser(requestUser);
		assertNotNull(rspu);
		assertEquals("12", rspu.getBody().getRole().getId());
	}

	@Test
	void testCreateManager() {
		Manager manager=new Manager("s", "wwd");
		when(manrepo.save(manager)).thenReturn(manager);
		ResponseEntity<Manager> entity= api.createManager(manager);
		assertNotNull(entity);
		assertEquals("s", entity.getBody().getEmpId());
	}

	@Test
	void testCreateReviewer() {
		Reviewer revi=new Reviewer("s", "wwd");
		when(reviwrepo.save(revi)).thenReturn(revi);
		ResponseEntity<Reviewer> entity= api.createReviewer(revi);
		assertNotNull(entity);
		assertEquals("s", entity.getBody().getEmpId());
	}

	@Test
	void testCreateRole() {
		Role rol=new Role("s", "wwd");
		when(rolerepo.save(rol)).thenReturn(rol);
		ResponseEntity<Role> entity= api.createRole(rol);
		assertNotNull(entity);
		assertEquals("s", entity.getBody().getId());
	}

	@Test
	void testGetRoles() {
		Role rol=new Role("s", "wwd");
		List<Role> lr=new ArrayList<>();
		lr.add(rol);
		when(rolerepo.findAll()).thenReturn(lr);
		List<Role> rols= api.getRoles();
		assertNotNull(rols);
		assertEquals(1, rols.size());
	}

	@Test
	void testGetManagers() {
		Manager mng=new Manager("s", "wwd");
		List<Manager> mr=new ArrayList<>();
		mr.add(mng);
		when(manrepo.findAll()).thenReturn(mr);
		List<Manager> mrs= api.getManagers();
		assertNotNull(mrs);
		assertEquals(1, mrs.size());
	}

	@Test
	void testGetReviewers() {
		Reviewer mng=new Reviewer("s", "wwd");
		List<Reviewer> mr=new ArrayList<>();
		mr.add(mng);
		when(reviwrepo.findAll()).thenReturn(mr);
		List<Reviewer> mrs= api.getReviewers();
		assertNotNull(mrs);
		assertEquals(1, mrs.size());
	}

}
