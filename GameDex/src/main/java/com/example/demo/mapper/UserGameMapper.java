package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserGameDto;
import com.example.demo.entity.Game;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGame;

@Service
public class UserGameMapper {

	public UserGameDto toDto(UserGame entity) {
		if(entity == null) {
			return null;
		}
		
		UserGameDto dto = new UserGameDto();
		dto.setId(entity.getId());
		dto.setRating(entity.getRating());
		dto.setUserId(entity.getUser().getId());
		dto.setGameId(entity.getGame().getId());
				
		return dto;
	}
	
	public UserGame fromDto(UserGameDto dto) {
		if(dto == null) {
			return null;
		}
		
		UserGame entity = new UserGame();
		
		entity.setId(dto.getId());
		entity.setRating(dto.getRating());
		
		User user = new User();
		user.setId(dto.getUserId());
		entity.setUser(user);
		
		Game game = new Game();
		game.setId(dto.getGameId());
		entity.setGame(game);
		
		return entity;
	}
	
	public List<UserGameDto> toDtoList(List<UserGame> entities){
		List<UserGameDto> dtos = new ArrayList<>();
		
		entities.forEach(student -> toDto(student));
		for(UserGame entity : entities) {
			dtos.add(toDto(entity));
		}
		
		return dtos;
	}
	
}
