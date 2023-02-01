package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserGameDto;
import com.example.demo.entity.UserGame;
import com.example.demo.mapper.UserGameMapper;
import com.example.demo.repository.UserGameRepo;

@Service
public class UserGameService {

	@Autowired
	UserGameRepo repository;
	
	@Autowired
	UserGameMapper mapper;
	
	public UserGameDto findById(long id) {
		UserGame userGame = repository.findById(id).orElse(null);
		UserGameDto userGameDto = mapper.toDto(userGame);
		return userGameDto;
	}
	
	 public List<UserGameDto> findAllUsersGames() {
	        
	        List<UserGame> usersGames = (ArrayList<UserGame>) repository.findAll();
	        
	        return mapper.toDtoList(usersGames);
	}
	 
	 public List<UserGameDto> findAllUserGamesByUser_id(long userId) {
	        
	        List<UserGame> usersGames = repository.findAllByUser_id(userId);
	        
	        return mapper.toDtoList(usersGames);
	}
	 
	 public List<UserGameDto> findAllUserGamesByGame_id(long gameId) {
	        
	        List<UserGame> usersGames = repository.findAllByGame_id(gameId);
	        
	        return mapper.toDtoList(usersGames);
	}
	
	
	public UserGameDto createUserGame(UserGameDto userGameDto) {
		
		UserGame userGame = mapper.fromDto(userGameDto);
		
		repository.save(userGame);
		
		return mapper.toDto(userGame);
	}
	
	public UserGameDto updateUserGame(UserGameDto userGameDto) {
		
		UserGame userGame = mapper.fromDto(userGameDto);
		
		repository.save(userGame);
		
		return mapper.toDto(userGame);
	}
	
	public void deleteUserGame(Long id) {
		repository.deleteById(id);
	}
	
}
