package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GameDto;
import com.example.demo.service.GameService;

@CrossOrigin(origins= {"http://127.0.0.1:5500","http://127.0.0.1:3000"})
@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@GetMapping("/{id}")
	public GameDto findById(@PathVariable(name="id") Long id) {		
		return gameService.findById(id);
	}
	
	@GetMapping("")
	public List<GameDto> findList() {		
		return gameService.findAllGames();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("")
	public GameDto createGame(@Valid @RequestBody GameDto gameDto) {		
		return gameService.createGame(gameDto);
	}
	
	//When updating an already existing entry, need to input the previous information, in addition to the changed information
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public GameDto updateGame(@Valid @RequestBody GameDto gameDto, @PathVariable(name ="id") Long id) {
		gameDto.setId(id);
		return gameService.updateGame(gameDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteGame(@PathVariable(name="id") Long id) {		
		gameService.deleteGame(id);
	}
	
	//Calling this request takes all the entries of the game's ratings and updates the data entry with the current average rating. Supposed to be done periodically, so it's possibly to notice and counteract any "review bombing".
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateRating/{id}")
	public GameDto updateGameRating(@PathVariable(name ="id") Long id) {
				
		return gameService.updateGameRatingById(id);
	}

}
