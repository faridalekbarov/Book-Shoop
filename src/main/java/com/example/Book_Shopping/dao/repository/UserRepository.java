package com.example.Book_Shopping.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_Shopping.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(Long id);
	
	Optional<User> findByUserName(String userName);
	boolean existsByUserName(String userName);
	
}
