package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DeveloperDto;
import com.example.demo.entity.Developer;
import com.example.demo.mapper.DeveloperMapper;
import com.example.demo.repository.DeveloperRepo;


@Service
public class DeveloperService {

	@Autowired
	DeveloperRepo repository;
	
	@Autowired
	DeveloperMapper mapper;
	
	public DeveloperDto findById(long id) {
		Developer developer = repository.findById(id).orElse(null);
		DeveloperDto developerDto = mapper.toDto(developer);
		return developerDto;
	}
	
	 public List<DeveloperDto> findAllDevelopers() {
	        
	        List<Developer> developers = (ArrayList<Developer>) repository.findAll();
	        
	        return mapper.toDtoList(developers);
	        
	}
	
	public DeveloperDto createDeveloper(DeveloperDto userDto) {
		
		Developer developer = mapper.fromDto(userDto);
		
		repository.save(developer);
		
		return mapper.toDto(developer);
	}
	
	public DeveloperDto updateDeveloper(DeveloperDto userDto) {
		
		Developer developer = mapper.fromDto(userDto);
		
		repository.save(developer);
		
		return mapper.toDto(developer);
	}
	
	public void deleteDeveloper(Long id) {
		repository.deleteById(id);
	}
}
