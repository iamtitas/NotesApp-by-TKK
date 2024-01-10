package com.nagarro.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.training.model.Users;
import com.nagarro.training.repository.AuthRepository;

@Service
public class AuthService {

	private final AuthRepository authRepository;

	@Autowired
	public AuthService(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	public Users authenticateUser(String username, String password) {
		Users user = authRepository.findByUsername(username);
		if (user == null || !user.getPassword().equals(password)) {
			throw new RuntimeException("Authentication failed");
		}
		return user;
	}
}
