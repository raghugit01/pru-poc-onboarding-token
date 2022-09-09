package com.pru.token.app.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "reviewer")
public class Reviewer {
	@Id 
	private String empId;
	
	private String reviewerName;
}
