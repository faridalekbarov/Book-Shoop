package com.example.Book_Shopping.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_Shopping.dao.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByUserId(Long userId);
	
	Order findByUserIdAndId(Long userId, Long id);
	
	List<Order> findByTotalPriceBetween(Double minTotalPrice, Double maxTotalPrice);
	
	Optional<Order> findById(Long id);

}
