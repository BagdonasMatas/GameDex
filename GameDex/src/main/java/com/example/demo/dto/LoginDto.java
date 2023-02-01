package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	
	private long id;
	
	private String email;
	
	private String password;
	
	private String token;
}
