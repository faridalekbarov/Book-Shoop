package com.example.Book_Shopping.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Book_Shopping.dao.entity.User;
import com.example.Book_Shopping.dao.repository.UserRepository;
import com.example.Book_Shopping.exception.BadRequestException;
import com.example.Book_Shopping.exception.NotFoundException;
import com.example.Book_Shopping.model.AuthResponse;
import com.example.Book_Shopping.model.LoginRequestDto;
import com.example.Book_Shopping.model.RegisterRequestDto;
import com.example.Book_Shopping.security.JwtUtils;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtils = jwtUtils;
	}

	public String registerUser(RegisterRequestDto registerRequest) {
		if (userRepository.existsByUserName(registerRequest.getUsername())) {
			throw new BadRequestException("Username is already taken");
		}

		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		userRepository.save(user);

		return "User registered successfully";
	}

	public AuthResponse loginUser(LoginRequestDto loginRequest) {
		User user = userRepository.findByUserName(loginRequest.getUsername())
				.orElseThrow(() ->{
					throw new NotFoundException("User not found with given id");
				});

		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}

		String token = jwtUtils.generateJwtToken(org.springframework.security.core.userdetails.User.builder()
				.username(user.getUserName()).password(user.getPassword()).build());
		AuthResponse response = new AuthResponse();
		response.setToken(token);
		return response;
	}
}
