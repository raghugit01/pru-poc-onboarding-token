package com.pru.token.app.user.api;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pru.token.app.user.Role;
import com.pru.token.app.user.RoleRepository;
import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;

@RestController
public class UserApi {

	@Autowired 
	private UserService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PutMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
		User createdUser = service.save(user);
		URI uri = URI.create("/users/" + createdUser.getId());
		
		UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getEmail());
		
		return ResponseEntity.created(uri).body(userDto);
	}
	
	@PostMapping("/user_add")
	public ResponseEntity<?> addUser(@RequestBody RequestUser requestUser){
		Role rolei = roleRepository.findById(requestUser.getRoleId()).get();
		User user=new User();
		user.setEmail(requestUser.getEmail());
		user.setRole(rolei);
		user.setEmployeeId(requestUser.getEmployeeId());
		user.setId(requestUser.getId());
		user.setManagerName(requestUser.getManagerName());
		user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
		user.setReviewerName(requestUser.getReviewerName());
		user.setUserName(requestUser.getUserName());
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/role_add")
	public ResponseEntity<?> createRole(@RequestBody Role role){
		roleRepository.save(role);
		return ResponseEntity.ok(role);
	}
}
