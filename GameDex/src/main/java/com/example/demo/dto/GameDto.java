package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

	private Long id;
	
	@NotBlank(message ="Name is required")
	private String name;
	
	@NotBlank(message ="Genre is required")
	private String genre;
	
	private Float averageRating;
	
	private Long developerId;
	
	private List<UserDto> gameUserFavorites;

}
