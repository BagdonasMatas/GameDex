package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGameDto {

	private Long id;
	
	private Long rating;
	
	private Long userId;
	
	private Long gameId;
	
	
}
