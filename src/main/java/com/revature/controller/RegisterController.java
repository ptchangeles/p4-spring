package com.revature.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.UserNotFoundException;
import com.revature.models.Users;
import com.revature.service.RegisterService;

@RestController
@RequestMapping("/register")
@CrossOrigin("*")
public class RegisterController {

	private RegisterService registerService;
	
	public RegisterController( RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@PostMapping
	public ResponseEntity<?> register(
			 @RequestParam(name = "email", required = true) String email,
	         @RequestParam(name = "password", required = true) String password,
	         @RequestParam(name = "firstName", required = true) String firstName,
	         @RequestParam(name = "lastName", required = true) String lastName,
	         @RequestParam(name = "address", required = true) String address	
	) throws UserNotFoundException{
		if(registerService.existByEmail(email)) {
			 return ResponseEntity.status(400).body("Email already taken.");
		}else {
			Users user = new Users();
			user.setEmail(email);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setAddress(address);
			return ResponseEntity.status(HttpStatus.CREATED).body(registerService.save(user));
	    }
	}
	
}
