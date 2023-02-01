package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DeveloperDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Developer;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@Service
public class DeveloperMapper {

	@Autowired
	UserService userService;
	
	public DeveloperDto toDto(Developer entity) {
		if(entity == null) {
			return null;
		}
		
		DeveloperDto dto = new DeveloperDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		
		dto.setDeveloperUserFavorites(userService.findAllByUserDeveloperFavorites_id(entity.getId()));

		
		return dto;
	}
	
	public Developer fromDto(DeveloperDto dto) {
		if(dto == null) {
			return null;
		}
		
		Developer entity = new Developer();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		
		List<User> users = new ArrayList<>();
		for(UserDto userDto : dto.getDeveloperUserFavorites()) {
			User user = new User();
			user.setId(userDto.getId());
			users.add(user);
		}
		
		return entity;
	}
	
	public List<DeveloperDto> toDtoList(List<Developer> entities){
		List<DeveloperDto> dtos = new ArrayList<>();
		
		entities.forEach(user -> toDto(user));
		for(Developer entity : entities) {
			dtos.add(toDto(entity));
		}
		
		return dtos;
	}
	
}
