package com.leavemanagement.LeaveManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leavemanagement.LeaveManagement.payload.JWTAuthResponse;
import com.leavemanagement.LeaveManagement.payload.LoginDto;
import com.leavemanagement.LeaveManagement.payload.UserDto;
import com.leavemanagement.LeaveManagement.security.JwtTokenProvider;
import com.leavemanagement.LeaveManagement.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	//post method to register manager
	@PostMapping("/register/manager")
	public ResponseEntity<UserDto> createManager(@Valid @RequestBody UserDto userDto){
		logger.info("Received request to /api/auth/register/manager");
		return new ResponseEntity<>(this.userService.createManager(userDto),HttpStatus.CREATED);
	}
	
	//post method to register employee
	@PostMapping("/register/employee")
	public ResponseEntity<UserDto> createEmployee(@Valid @RequestBody UserDto userDto){
		logger.info("Received request to /api/auth/register/employee");
		return new ResponseEntity<>(this.userService.createEmployee(userDto),HttpStatus.CREATED);
	}
	
	//post method to login
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> loginUser(@Valid @RequestBody LoginDto loginDto){
		logger.info("Received request to /api/auth/login");
		Authentication authentication = 
				authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
				);
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("After authentication");
		String token = jwtTokenProvider.generateToken(authentication);
		System.out.println("Token : "+token);
		return ResponseEntity.ok(new JWTAuthResponse(token));
	}
}
