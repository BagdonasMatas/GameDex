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

import com.example.demo.dto.DeveloperDto;
import com.example.demo.service.DeveloperService;


@CrossOrigin(origins= {"http://127.0.0.1:5500","http://127.0.0.1:3000"})
@RestController
@RequestMapping("/developer")
public class DeveloperController {

	@Autowired
	DeveloperService developerService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public DeveloperDto findById(@PathVariable(name="id") Long id) {		
		return developerService.findById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("")
	public List<DeveloperDto> findList() {		
		return developerService.findAllDevelopers();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("")
	public DeveloperDto createDeveloper(@Valid @RequestBody DeveloperDto developerDto) {		
		return developerService.createDeveloper(developerDto);
	}
	
	//When updating an already existing entry, need to input the previous information, in addition to the changed information
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public DeveloperDto updateDeveloper(@Valid @RequestBody DeveloperDto developerDto, @PathVariable(name ="id") Long id) {		
		return developerService.updateDeveloper(developerDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteDeveloper(@PathVariable(name="id") Long id) {		
		developerService.deleteDeveloper(id);
	}
	
}
