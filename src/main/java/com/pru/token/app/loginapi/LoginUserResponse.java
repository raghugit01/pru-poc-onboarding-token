package com.pru.token.app.loginapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUserResponse {
	
	private String role;
	private String email;
	private String name;
	private String userId;
	private String reviewer;
	private String manager;
	private String token;

}
