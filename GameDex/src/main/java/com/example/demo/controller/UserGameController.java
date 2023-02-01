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

import com.example.demo.dto.UserGameDto;
import com.example.demo.service.UserGameService;

@CrossOrigin(origins= {"http://127.0.0.1:5500","http://127.0.0.1:3000"})
@RestController
@RequestMapping("/usergame")
public class UserGameController {
	
	@Autowired
	UserGameService userGameService;

	@GetMapping("/{id}")
	public UserGameDto findById(@PathVariable(name="id") Long id) {		
		return userGameService.findById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("")
	public List<UserGameDto> findList() {		
		return userGameService.findAllUsersGames();
	}
	
	@PostMapping("")
	public UserGameDto createUserGame(@Valid @RequestBody UserGameDto userGameDto) {		
		return userGameService.createUserGame(userGameDto);
	}
	
	//When updating an already existing entry, need to input the previous information, in addition to the changed information
	@PutMapping("/{id}")
	public UserGameDto updateUserGame(@Valid @RequestBody UserGameDto userGameDto, @PathVariable(name ="id") Long id) {
		userGameDto.setId(id);
		return userGameService.updateUserGame(userGameDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteUserGame(@PathVariable(name="id") Long id) {		
		userGameService.deleteUserGame(id);
	}
	
	//Method used to get all of one user's games as a list.
	@GetMapping("/UserGames/{id}")
	public List<UserGameDto> findUserGamesList(@PathVariable(name="id") Long id) {		
		return userGameService.findAllUserGamesByUser_id(id);
	}
	//Method used to get all of a single game's users as a list.
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/GameUsers/{id}")
	public List<UserGameDto> findGameUsersList(@PathVariable(name="id") Long id) {		
		return userGameService.findAllUserGamesByGame_id(id);
	}
	
}
