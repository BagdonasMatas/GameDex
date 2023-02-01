package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.UserDto;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.service.AuthService;

@CrossOrigin(origins= {"http://127.0.0.1:5500","http://127.0.0.1:3000"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) throws Exception{
		Authentication authentication = authenticationManager
		        .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwtToken = jwtUtils.generateJwtToken(userDetails);
		loginDto.setPassword(null);
		loginDto.setToken(jwtToken);
		loginDto.setId(userDetails.getId());
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken).body(loginDto);
	}
	
	@PostMapping("/signup")
	public UserDto signup(@Valid @RequestBody SignupDto signupDto) throws Exception{
		return authService.signup(signupDto);
	}
	
	
	
	
}
