package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DeveloperDto;
import com.example.demo.dto.GameDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Developer;
import com.example.demo.entity.Game;
import com.example.demo.entity.User;

@Service
public class UserMapper {

	public UserDto toDto(User entity) {
		if(entity == null) {
			return null;
		}
		
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		
		/*
		Issue fixed via putting in new Arraylist declaration on the arrays in the user entity itself.
		//In the case that a user is signing up, the toDto function is called, however, the lists of favorite developers and games are null, so the function returns all the information aside from those two to avoid an error.
		if(entity.getUserGameFavorites()==null) {
			return dto;
		}
		if(entity.getUserDeveloperFavorites()==null) {
			return dto;
		}
		*/
		dto.setUserGameFavorites(ToGameDtoList(entity.getUserGameFavorites()));
		dto.setUserDeveloperFavorites(ToDeveloperDtoList(entity.getUserDeveloperFavorites()));

		
		return dto;
	}
	
	public User fromDto(UserDto dto) {
		if(dto == null) {
			return null;
		}
		
		User entity = new User();
		entity.setId(dto.getId());
		entity.setUsername(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		
		List<Game> favoriteGames = new ArrayList<>();
		for(GameDto gameDto : dto.getUserGameFavorites()) {
			Game game = new Game();
			game.setId(gameDto.getId());
			favoriteGames.add(game);
		}
		
		entity.setUserGameFavorites(favoriteGames);
		
		List<Developer> favoriteDevelopers = new ArrayList<>();
		for(DeveloperDto developerDto : dto.getUserDeveloperFavorites()) {
			Developer developer = new Developer();
			developer.setId(developerDto.getId());
			favoriteDevelopers.add(developer);
		}
		
		entity.setUserDeveloperFavorites(favoriteDevelopers);
		
		return entity;
	}
	
	public List<UserDto> toDtoList(List<User> entities){
		List<UserDto> dtos = new ArrayList<>();
		
		entities.forEach(user -> toDto(user));
		for(User entity : entities) {
			dtos.add(toDto(entity));
		}
		
		return dtos;
	}
	
	public GameDto ToGameDto(Game entity) {
		
		GameDto dto = new GameDto();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAverageRating(entity.getAverageRating());
		dto.setGenre(entity.getGenre());
		if(entity.getDeveloper()!= null) {
		dto.setDeveloperId(entity.getDeveloper().getId());
		}
		return dto;
	}
	
	public List<GameDto> ToGameDtoList(List<Game> entities){
		List<GameDto> dtos = new ArrayList<>();
			
		entities.forEach(game -> ToGameDto(game));
		for(Game entity : entities) {
			dtos.add(ToGameDto(entity));
		}
			
		return dtos;
	}
	
	public DeveloperDto ToDeveloperDto(Developer entity) {
		
		DeveloperDto dto = new DeveloperDto();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		
		return dto;
	}
	
	public List<DeveloperDto> ToDeveloperDtoList(List<Developer> entities){
		List<DeveloperDto> dtos = new ArrayList<>();
			
		entities.forEach(developer -> ToDeveloperDto(developer));
		for(Developer entity : entities) {
			dtos.add(ToDeveloperDto(entity));
		}
			
		return dtos;
	}
	
	
}
