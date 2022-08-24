package com.pru.token.app.loginapi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pru.token.app.jwt.JwtTokenUtil;
import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;

@Service
public class AuthenticatedUser {
	
	@Autowired 
	private AuthenticationManager authManager;
	
	@Autowired 
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private UserRepository repository;

	public LoginUserResponse getUser(String id) {
		Optional<User> optional=repository.findByEmployeeId(id);
		LoginUserResponse response=new LoginUserResponse();
		if (optional.isPresent()) {
			User request=optional.get();
			System.out.println("User "+optional.get());
			try {
				Authentication authentication = authManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								request.getEmail(), "abc")
				);
				
				User user = (User) authentication.getPrincipal();
				String accessToken = jwtUtil.generateAccessToken(user);
				response.setEmail(user.getEmail());
				response.setManager(user.getManagerName());
				response.setName(user.getUserName());
				response.setReviewer(user.getReviewerName());
				response.setRole(user.getRole().getName());
				response.setToken(accessToken);
				response.setUserId(user.getEmployeeId());				
			}catch (Exception e) {
				System.out.println("in exception "+e.getMessage());
			}
		}else {
			System.out.println("user not tere "+id);
		}
		return response;
	}

	
}
