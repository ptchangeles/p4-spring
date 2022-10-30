package com.revature.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.exception.UserNotFoundException;
import com.revature.models.Users;
import com.revature.repository.UserRepository;

@Service
public class RegisterService {

	private final UserRepository userRepository;
	
	public RegisterService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Users save (Users user) {
		return userRepository.save(user);
	}
	
	public boolean existByEmail(String email) throws UserNotFoundException {
		Optional<Users> user = userRepository.existsByEmail(email);
		if (user.isPresent()) {
			return true;
		} else {
			throw new UserNotFoundException("User Not Found");
		}
	}
}
