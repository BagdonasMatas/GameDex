package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GameDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Developer;
import com.example.demo.entity.Game;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class GameMapper {
	
	@Autowired
	UserService userService;

	public GameDto toDto(Game entity) {
		if(entity == null) {
			return null;
		}
		
		GameDto dto = new GameDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setGenre(entity.getGenre());
		dto.setAverageRating(entity.getAverageRating());
		dto.setDeveloperId(entity.getDeveloper().getId());
		
		dto.setGameUserFavorites(userService.findAllByUserGameFavorites_id(entity.getId()));
		
		return dto;
	}
	
	public Game fromDto(GameDto dto) {
		if(dto == null) {
			return null;
		}
		
		Game entity = new Game();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setGenre(dto.getGenre());
		entity.setAverageRating(dto.getAverageRating());
		
		Developer developer = new Developer();
		developer.setId(dto.getDeveloperId());
		entity.setDeveloper(developer);
		
		List<User> users = new ArrayList<>();
		for(UserDto userDto : dto.getGameUserFavorites()) {
			User user = new User();
			user.setId(userDto.getId());
			users.add(user);
		}
		
		entity.setGameUserFavorites(users);
		
		return entity;
	}
	
	public List<GameDto> toDtoList(List<Game> entities){
		List<GameDto> dtos = new ArrayList<>();
		
		entities.forEach(user -> toDto(user));
		for(Game entity : entities) {
			dtos.add(toDto(entity));
		}
		
		return dtos;
	}
}
