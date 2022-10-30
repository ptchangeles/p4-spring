package com.revature.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{


	Optional<Users> findByEmailAndPassword(String email, String password);

	Optional<Users> existsByEmail(String email);

}
