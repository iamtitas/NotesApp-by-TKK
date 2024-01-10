package com.nagarro.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nagarro.training.model.Users;
import com.nagarro.training.service.AuthService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public Users login(@RequestBody Users loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		Users authenticatedUser = authService.authenticateUser(username, password);
		return authenticatedUser;
	}
}
