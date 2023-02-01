package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDto {
	
	private Long id;
	
	@NotBlank(message ="Name is required")
	private String name;

	private List<UserDto> developerUserFavorites;
	
}
