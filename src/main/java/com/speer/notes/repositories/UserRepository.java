package com.speer.notes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speer.notes.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String userName);

}
