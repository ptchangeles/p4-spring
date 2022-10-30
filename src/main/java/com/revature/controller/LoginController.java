package com.revature.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.LoginDTO;
import com.revature.models.Users;
import com.revature.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping
	public ResponseEntity<Users> login (@RequestBody LoginDTO loginRequest, HttpSession session) {
		Optional<Users> user = loginService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());
		
        if(!user.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", user.get());

        return ResponseEntity.ok(user.get());
    }
	
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }
}
