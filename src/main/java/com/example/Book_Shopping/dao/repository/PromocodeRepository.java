package com.example.Book_Shopping.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_Shopping.dao.entity.Promocode;

public interface PromocodeRepository extends JpaRepository<Promocode, Long> {
	Optional<Promocode> findByCode(String code);
	Optional<Promocode> findById(Long id);
}
