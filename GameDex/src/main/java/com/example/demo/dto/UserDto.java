package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;
	
	@NotBlank(message ="Email is required")
	@Email
	private String email;
	
	@NotBlank(message ="Username is required")
	private String username;
	
	private String password;
	
	private List<GameDto> userGameFavorites;
	
	private List<DeveloperDto> userDeveloperFavorites;
	
}
