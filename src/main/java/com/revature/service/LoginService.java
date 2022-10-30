package com.revature.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.models.Users;
import com.revature.repository.UserRepository;

@Service
public class LoginService {

	private final UserRepository userRepository;
	
	
	public LoginService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Optional<Users> findByCredentials(String email, String password){
		return userRepository.findByEmailAndPassword(email, password);
	}
	
}
