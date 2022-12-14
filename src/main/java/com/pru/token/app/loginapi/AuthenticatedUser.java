package com.pru.token.app.loginapi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pru.token.app.jwt.JwtTokenUtil;
import com.pru.token.app.user.LogoutUserToken;
import com.pru.token.app.user.LogoutUserTokenRepository;
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
	
	@Autowired
	private LogoutUserTokenRepository logoutUserRepo;

	public LoginUserResponse getUser(LoginUserRequest req) {
		Optional<User> optional=repository.findByEmployeeId(req.getEmpId());
		LoginUserResponse response=new LoginUserResponse();
		if (optional.isPresent()) {
			User request=optional.get();
			System.out.println("User "+optional.get());
			try {
				Authentication authentication = authManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								request.getEmail(), req.getPassword())
				);
				
				User user = (User) authentication.getPrincipal();
				String accessToken = jwtUtil.generateAccessToken(user);
				response.setEmail(user.getEmail());
				response.setManager(user.getManager());
				response.setName(user.getUserName());
				response.setReviewer(user.getReviewer());
				response.setRole(user.getRole().getName());
				response.setToken(accessToken);
				response.setUserId(user.getEmployeeId());				
				LogoutUserToken logoutUserToken = new LogoutUserToken();
				logoutUserToken.setLogout(false);
				logoutUserToken.setToken(accessToken);
				LogoutUserToken out= logoutUserRepo.save(logoutUserToken);
				System.out.println("in out : "+out.getToken()+" , isout "+out.isLogout()+" , tid "+out.getTId());
			}catch (Exception e) {
				System.out.println("in exception "+e.getMessage());
			}
		}else {
			System.out.println("user not tere "+req.getEmpId());
		}
		return response;
	}

	
}
