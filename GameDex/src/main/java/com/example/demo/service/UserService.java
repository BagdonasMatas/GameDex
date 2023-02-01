package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo repository;
	
	@Autowired
	UserMapper mapper;
	
	public UserDto findById(long id) {
		User user = repository.findById(id).orElse(null);
		UserDto userDto = mapper.toDto(user);
		return userDto;
	}
	
	 public List<UserDto> findAllUsers() {
	        
	        List<User> users = (ArrayList<User>) repository.findAll();
	        
	        return mapper.toDtoList(users);
	        
	}
	
	public UserDto createUser(UserDto userDto) {
		
		User user = mapper.fromDto(userDto);
		
		repository.save(user);
		
		return mapper.toDto(user);
	}
	
	public UserDto updateUser(UserDto userDto) {
		
		User user = mapper.fromDto(userDto);
		
		repository.save(user);
		
		return mapper.toDto(user);
	}
	
	public void deleteUser(Long id) {
		repository.deleteById(id);
	}
	
	public List<UserDto> findAllByUserGameFavorites_id(Long gameId){
		List<User> users = repository.findAllByUserGameFavorites_id(gameId);
		return mapper.toDtoList(users);
	}
	
	public List<UserDto> findAllByUserDeveloperFavorites_id(Long developerId){
		List<User> users = repository.findAllByUserDeveloperFavorites_id(developerId);
		return mapper.toDtoList(users);
	}
	
	//Not used currently as controller cannot be accessed by a get method because of CORS blocking "@" sign in the link which is needed to send an email, as get requests can't have a body.
	public UserDto findByEmail(String email){
		Optional<User> user = repository.findByEmail(email);
		return mapper.toDto(user.get());
	}
}
