package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GameDto;
import com.example.demo.dto.UserGameDto;
import com.example.demo.entity.Game;
import com.example.demo.mapper.GameMapper;
import com.example.demo.repository.GameRepo;

@Service
public class GameService {

	@Autowired
	GameRepo repository;
	
	@Autowired
	GameMapper mapper;
	
	@Autowired
	UserGameService userGameService;
	
	public GameDto findById(long id) {
		Game game = repository.findById(id).orElse(null);
		GameDto gameDto = mapper.toDto(game);
		return gameDto;
	}
	
	 public List<GameDto> findAllGames() {
	        
	        List<Game> users = (ArrayList<Game>) repository.findAll();
	        
	        return mapper.toDtoList(users);
	        
	}
	
	public GameDto createGame(GameDto gameDto) {
		
		Game game = mapper.fromDto(gameDto);
		
		repository.save(game);
		
		return mapper.toDto(game);
	}
	
	public GameDto updateGame(GameDto gameDto) {
		
		Game game = mapper.fromDto(gameDto);
		
		repository.save(game);
		
		return mapper.toDto(game);
	}
	
	public void deleteGame(Long id) {
		repository.deleteById(id);
	}
	
	//Method that uses the list of all ratings different users gave game to get the average rating
	public GameDto updateGameRatingById(Long id) {
		
		//Creating a list of UserGame entries of the game that contain all the ratings the game has received.
		List<UserGameDto> gameUsers = userGameService.findAllUserGamesByGame_id(id);
		float avg = 0;

		//Cycle to add all the ratings of different users together
		for(int i = 0 ; i < gameUsers.size() ; i ++) {
			avg += gameUsers.get(i).getRating();	
		}
		//Calculating the average by dividing the sum of all ratings by amount of users that have rated the game.
		avg = avg/gameUsers.size();
		
		Game game = repository.findById(id).orElse(null);
		game.setAverageRating(avg);
		
		repository.save(game);
		
		return mapper.toDto(game);		
	}
	
	
}
