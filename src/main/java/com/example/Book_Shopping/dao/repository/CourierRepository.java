package com.example.Book_Shopping.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_Shopping.dao.entity.Courier;

public interface CourierRepository extends JpaRepository<Courier, Long> {

	Optional<Courier> findById(Long id);
	List<Courier> findByIdIn(List<Long> Ids);
}
