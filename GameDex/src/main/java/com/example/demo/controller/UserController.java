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

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

@CrossOrigin(origins= {"http://127.0.0.1:5500","http://127.0.0.1:3000"})
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public UserDto findById(@PathVariable(name="id") Long id) {		
		return userService.findById(id);
	}
	
	@GetMapping("")
	public List<UserDto> findList() {		
		return userService.findAllUsers();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("")
	public UserDto createUser(@Valid @RequestBody UserDto userDto) {		
		return userService.createUser(userDto);
	}
	
	//When updating an already existing entry, need to input the previous information, in addition to the changed information
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public UserDto updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(name ="id") Long id) {		
		userDto.setId(id);
		userDto.setPassword(userService.findById(id).getPassword());
		return userService.updateUser(userDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(name="id") Long id) {		
		userService.deleteUser(id);
	}
	
	//Not used currently as controller cannot be accessed by a get method because of CORS blocking "@" sign in the link which is needed to send an email, as get requests can't have a body.
	@GetMapping("/byEmail/{email}")
	public UserDto findByEmail(@Valid @PathVariable(name="email") String email) {		
		return userService.findByEmail(email);
	}
}
