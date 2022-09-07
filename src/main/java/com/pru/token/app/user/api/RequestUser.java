package com.pru.token.app.user.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {

	private Integer id;
	
	private String email;
	
	private String employeeId;
	
	private String reviewerName;
	
	private String managerName;
	
	private String userName;
	
	private String password;
	
	private Integer roleId;
}
