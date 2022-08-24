package com.pru.token.app.loginapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginuser")
public class LoginApi {
	
	@Autowired
	private AuthenticatedUser user;

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable String id){
		System.out.println("entr loginusr "+id);
		LoginUserResponse response=user.getUser(id);
		return ResponseEntity.ok().body(response);
	}
}
