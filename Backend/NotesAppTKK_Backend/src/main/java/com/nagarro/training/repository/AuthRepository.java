package com.nagarro.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.training.model.Users;

public interface AuthRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);

	@Query("SELECT u.username FROM Users u")
	List<String> findAllUsernames();

}
